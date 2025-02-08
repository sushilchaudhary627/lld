package services;

import models.User;
import repository.UserRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private final UserRepository userRepository;
    private final AtomicInteger userIdGenerator = new AtomicInteger(1);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     *
     * @param name   The name of the user.
     * @param mobile The mobile number of the user.
     * @return The created user.
     */
    public User createUser(String name, String mobile) {
        validateUserInput(name, mobile);

        Optional<User> userExist = userRepository.getUserByMobile(mobile);
        if (userExist.isPresent()) {
            throw new IllegalArgumentException("User is already created with this mobile number.");
        }

        Integer userId = userIdGenerator.getAndIncrement();
        User user = new User(userId, name, mobile);
        userRepository.save(user);
        return user;
    }

    /**
     * Validates user input for creation.
     *
     * @param name   The name of the user.
     * @param mobile The mobile number of the user.
     */
    private void validateUserInput(String name, String mobile) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (Objects.isNull(mobile) || mobile.isEmpty()) {
            throw new IllegalArgumentException("Mobile is required");
        }
    }

    /**
     * Gets a user by ID.
     *
     * @param id The ID of the user.
     * @return The user.
     */
    public User getUserById(Integer id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }

    /**
     * Updates the pending expenses for users involved in a transaction.
     *
     * @param paidByUserId The user ID who paid.
     * @param toUserId     The user ID to receive the payment.
     * @param amount       The amount paid.
     */
    public void updateExpensePending(Integer paidByUserId, Integer toUserId, Double amount) {
        updateUserExpenses(paidByUserId, toUserId, amount, true);
    }

    /**
     * Updates the received expenses for users involved in a transaction.
     *
     * @param paidByUserId The user ID who paid.
     * @param toUserId     The user ID to receive the payment.
     * @param amount       The amount received.
     */
    public void updateExpenseReceived(Integer paidByUserId, Integer toUserId, Double amount) {
        updateUserExpenses(paidByUserId, toUserId, amount, false);
    }

    /**
     * Helper method to update user expenses.
     *
     * @param paidByUserId The user ID who paid.
     * @param toUserId     The user ID to receive the payment.
     * @param amount       The amount involved in the transaction.
     * @param isPending    True if updating pending expenses, false if updating received expenses.
     */
    private void updateUserExpenses(Integer paidByUserId, Integer toUserId, Double amount, boolean isPending) {
        User paidBy = getUserById(paidByUserId);
        User toUser = getUserById(toUserId);

        if (isPending) {
            toUser.setTotalExpensesPending(toUser.getTotalExpensesPending() + amount);
            paidBy.setTotalExpensesPendingByOther(paidBy.getTotalExpensesPendingByOther() + amount);
        } else {
            toUser.setTotalExpensesReceived(toUser.getTotalExpensesReceived() + amount);
            toUser.setTotalExpensesPendingByOther(toUser.getTotalExpensesPendingByOther() - amount);
            paidBy.setTotalExpensesPending(paidBy.getTotalExpensesPending() - amount);
        }
    }
}
