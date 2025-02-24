package services;

import constants.SplitType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import models.Expense;
import models.Split;
import repo.ExpenseRepo;

public class ExpenseService {
    private final AtomicInteger expenseIdGenerator;
    private final SplitService splitService;
    private final UserService userService;
    private final ExpenseRepo expenseRepo;


    public ExpenseService(AtomicInteger expenseIdGenerator, SplitService splitService, UserService userService, ExpenseRepo expenseRepo) {
        this.expenseIdGenerator = expenseIdGenerator;
        this.splitService = splitService;
        this.userService = userService;
        this.expenseRepo = expenseRepo;
    }

    public Expense createExpense(String expenseName, Map<Integer, Double>splitDetails, Integer createdUserId, Double totalAmount, SplitType splitType){
        validateExpenseCreateReq(expenseName);
        Expense expense = new Expense(expenseIdGenerator.getAndIncrement(), userService.findUserById(createdUserId));
        List<Split>splits = splitService.createSplit(splitDetails, totalAmount, splitType);
        expense.setSplits(splits);
        expense.setTotalAmount(totalAmount);
        expense.setExpenseName(expenseName);
        expense.validate();
        splitService.markPaid(splits.stream()
            .filter(split -> split.getUser().getUserId().equals(createdUserId))
            .findFirst()
            .orElseThrow( () -> new RuntimeException("Created user should have some split"))
        );
        userService.updateExpenseTrackerOnCreate(expense);
        expenseRepo.save(expense);
        return expense;
    }

    public Expense markPaid(Integer expenseId, Integer splitId){
        Expense expense = expenseRepo.findExpenseById(expenseId).orElseThrow();
        Split split = expense.getSplits().stream().
            filter(s -> s.getSplitId().equals(splitId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Split not found"));
       splitService.markPaid(split);
       userService.updateExpenseTrackerOnSplitPayment(split, expense);
       expenseRepo.save(expense);
        return expense;
    }

    private void validateExpenseCreateReq(String expenseName){
        if(expenseName == null || expenseName.isBlank()){
           throw new RuntimeException("Expense name is required and can't be empty.");
        }
        if(expenseRepo.findExpenseByName(expenseName).isPresent()){
            throw new RuntimeException("Expense with same name already exist");
        }
    }
}
