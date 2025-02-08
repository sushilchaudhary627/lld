package services;

import models.*;
import repository.ExpenseRepository;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserService userService;
    private final AtomicInteger expenseIdGenerator = new AtomicInteger(1);
    private final AtomicInteger splitIdGenerator = new AtomicInteger(1);

    public ExpenseService(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    /**
     * Creates a new expense based on the provided details and splits.
     *
     * @param name     The name of the expense.
     * @param userId   The user creating the expense.
     * @param amount   The total amount of the expense.
     * @param splits   A map of users and their respective split amounts or percentages.
     * @param type     The type of split (EXACT, EQUAL, PERCENTAGE).
     * @return         The created expense.
     */
    public Expense createExpense(String name, Integer userId, Double amount, Map<Integer, Double> splits, String type) {
        SplitType splitType = SplitType.fromString(type);
        validateSplits(splitType, amount, splits);

        User createdBy = userService.getUserById(userId);
        Integer expenseId = expenseIdGenerator.getAndIncrement();

        // Create the expense based on the split type
        Expense expense = createExpense(expenseId, name, createdBy, amount, splitType);
        addSplitsToExpense(expense, splits, amount);
        expenseRepository.save(expense);

        return expense;
    }

    /**
     * Validates if the splits are correct based on the split type.
     *
     * @param splitType The type of the split (EXACT, EQUAL, PERCENTAGE).
     * @param amount    The total amount of the expense.
     * @param splits    A map of user splits.
     */
    void validateSplits(SplitType splitType, Double amount, Map<Integer, Double> splits) {
        Double sum = splits.values().stream().mapToDouble(Double::doubleValue).sum();
        final double EPSILON = 0.0001;

        if (splitType.equals(SplitType.EXACT) || splitType.equals(SplitType.EQUAL)) {
            if (Math.abs(sum - amount) > EPSILON) {
                throw new IllegalArgumentException("Invalid split received: Sum of splits doesn't match the total amount.");
            }
        } else {
            if (Math.abs(sum - 100.00) > EPSILON) {
                throw new IllegalArgumentException("Invalid split received: Percentage splits don't sum to 100.");
            }
        }
    }

    /**
     * Creates an expense based on the split type.
     *
     * @param id        The ID of the expense.
     * @param name      The name of the expense.
     * @param createdBy The user who created the expense.
     * @param amount    The total amount of the expense.
     * @param splitType The split type.
     * @return          The created expense.
     */
    private Expense createExpense(Integer id, String name, User createdBy, Double amount, SplitType splitType) {
        switch (splitType) {
            case EXACT:
                return new ExactExpense(id, name, createdBy, amount);
            case EQUAL:
                return new EqualExpense(id, name, createdBy, amount);
            case PERCENTAGE:
                return new PercentExpense(id, name, createdBy, amount);
            default:
                throw new IllegalArgumentException("Unsupported split type");
        }
    }

    /**
     * Adds splits to an expense.
     *
     * @param expense   The expense object.
     * @param splits    A map of user splits.
     * @param totalAmount The total amount of the expense.
     */
    private void addSplitsToExpense(Expense expense, Map<Integer, Double> splits, double totalAmount) {
        for (Map.Entry<Integer, Double> entry : splits.entrySet()) {
            Integer id = splitIdGenerator.getAndIncrement();
            User user = userService.getUserById(entry.getKey());
            Double splitAmount = calculateSplitAmount(expense, entry.getValue(), totalAmount);
            Split split = new Split(id, user, SplitStatus.PENDING, splitAmount);
            if (!expense.getCreatedBy().getUserId().equals(user.getUserId())) {
                userService.updateExpensePending(expense.getCreatedBy().getUserId(), user.getUserId(), splitAmount);
            } else{
                // mark for who paid expenses
                split.setSplitStatus(SplitStatus.PAID);
            }
            expense.addSplit(split);
        }
    }

    /**
     * Calculates the split amount based on whether it's a percentage or exact amount.
     *
     * @param expense               The expense object.
     * @param splitPercentageOrAmount The amount or percentage for the split.
     * @param totalAmount           The total amount of the expense.
     * @return                      The calculated split amount.
     */
    private Double calculateSplitAmount(Expense expense, Double splitPercentageOrAmount, double totalAmount) {
        if (expense instanceof PercentExpense) {
            return (splitPercentageOrAmount * totalAmount) / 100;
        }
        return splitPercentageOrAmount;
    }

    /**
     * Marks a split as paid by the user and updates the relevant amounts.
     *
     * @param expenseId   The ID of the expense.
     * @param paidByUserId The ID of the user marking the split as paid.
     * @return            The updated expense.
     */
    public Expense markPaidSplit(Integer expenseId, Integer paidByUserId) {
        Expense expense = expenseRepository.getExpenseById(expenseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expense"));

        Split split = expense.getSplits().stream()
                .filter(s -> s.getUser().getUserId().equals(paidByUserId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expense does not belong to user"));

        split.setSplitStatus(SplitStatus.PAID);
        userService.updateExpenseReceived(paidByUserId, expense.getCreatedBy().getUserId(), split.getAmount());
        expenseRepository.update(expense);

        return expense;
    }
}
