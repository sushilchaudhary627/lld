package models;

public class ExactExpense extends  Expense{
    public ExactExpense(Integer id, String expenseName, User createdBy, Double amount) {
        super(id, expenseName, createdBy, amount);
    }
}
