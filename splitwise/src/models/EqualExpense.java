package models;

public class EqualExpense extends  Expense{
    public EqualExpense(Integer id, String expenseName, User createdBy, Double amount) {
        super(id, expenseName, createdBy, amount);
    }
}
