package models;

public class PercentExpense extends Expense{
    public PercentExpense(Integer id, String expenseName, User createdBy, Double amount) {
        super(id, expenseName, createdBy, amount);
    }
}
