package models;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private final Integer expenseId;
    private final User createdBy;
    private Double totalAmount;
    private List<Split> splits;
    private String expenseName;

    public Expense(Integer expenseId, User createdBy) {
        this.expenseId = expenseId;
        this.createdBy = createdBy;
        this.splits = new ArrayList<>();
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public void addSplit(Split split){
        splits.add(split);
    }

    public boolean isAnySplitsPending(){
        return splits.stream()
            .anyMatch(Split::isPending);
    }

    public void validate(){
        Double total = splits.stream().mapToDouble(Split::getAmount).sum();
        if(Math.abs(total-getTotalAmount()) > 0.01) {
            throw new RuntimeException("Not valid splits.");
        }

    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    @Override
    public String toString() {
        return "Expense{" +
            "expenseId=" + expenseId +
            ", createdBy=" + createdBy +
            ", totalAmount=" + totalAmount +
            ", splits=" + splits +
            ", expenseName='" + expenseName + '\'' +
            '}';
    }
}
