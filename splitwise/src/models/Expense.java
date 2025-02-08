package models;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private Integer id;
    private String expenseName;
    private User createdBy;
    private Double amount;
    private List<Split> splits;

    public Expense(Integer id, String expenseName, User createdBy, Double amount) {
        this.id = id;
        this.expenseName = expenseName;
        this.createdBy = createdBy;
        this.amount = amount;
        this.splits = new ArrayList<>();
    }

    public Expense() {

    }

    public void addSplit(Split split){
        splits.add(split);
    }

    public Integer getId() {
        return id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Double getAmount() {
        return amount;
    }

    public List<Split> getSplits() {
        return splits;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseName='" + expenseName + '\'' +
                ", createdBy=" + createdBy +
                ", amount=" + amount +
                ", splits=" + splits +
                '}';
    }
}
