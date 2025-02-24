package models;

public class User {
    private final Integer userId;
    private String name;
    private Double expenseAmount;
    private Double pendingAmount;
    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
        this.expenseAmount = 0.0;
        this.pendingAmount = 0.0;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Double getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(Double pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    @Override
    public String toString() {
        return "\nUser{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", expenseAmount=" + expenseAmount +
            ", pendingAmount=" + pendingAmount +
            '}';
    }
}
