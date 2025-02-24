package services;

import constants.SplitStatus;
import models.Expense;
import models.Split;
import models.User;
import repo.UserRepo;

import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private final AtomicInteger userIdGenerator;
    private final UserRepo userRepo;

    public UserService(AtomicInteger userIdGenerator, UserRepo userRepo) {
        this.userIdGenerator = userIdGenerator;
        this.userRepo = userRepo;
    }

    public User createUser(String name){
        validateCreateUserRequest(name);
        User user = new User(userIdGenerator.getAndIncrement(), name);
        userRepo.save(user);
        return user;
    }

    private void validateCreateUserRequest(String name) {
        if(name == null || name.isBlank()){
            throw new RuntimeException("Name is required and should be not empty...");
        }
        if(userRepo.findUserByName(name).isPresent()){
            throw new RuntimeException("User with same name already exists..");
        }
    }

    public  User findUserById(Integer createdUserId) {
        return userRepo.findUserById(createdUserId).orElseThrow();
    }

    public void updateExpenseTrackerOnCreate(Expense expense) {
        User createdUser = expense.getCreatedBy();
        createdUser.setExpenseAmount(createdUser.getExpenseAmount() + expense.getTotalAmount());
        for(Split split:expense.getSplits()){
              User user = split.getUser();
              if(SplitStatus.PENDING == split.getSplitStatus()){
                  user.setPendingAmount(user.getPendingAmount() + split.getAmount());
                  userRepo.update(user);
              }
        }
    }

    public void updateExpenseTrackerOnSplitPayment(Split split, Expense expense){
        if(!expense.getCreatedBy().getUserId().equals(split.getUser().getUserId())){
            User createdBy  = expense.getCreatedBy();
            createdBy.setExpenseAmount(createdBy.getExpenseAmount() - split.getAmount());
            User splitUser = split.getUser();
            splitUser.setPendingAmount(splitUser.getPendingAmount()-split.getAmount());
            splitUser.setExpenseAmount(splitUser.getExpenseAmount() + split.getAmount());
            userRepo.update(createdBy);
            userRepo.update(split.getUser());
        }
    }
}
