package models;

public class User {
    private Integer userId;

    public User(Integer userId, String name, String mobile) {
        this.userId = userId;
        this.name = name;
        this.mobile = mobile;
    }

    private String name;
    private String mobile;
    private String email;
    private Double totalExpensesPending = 0.00;

    public Double getTotalExpensesPending() {
        return totalExpensesPending;
    }

    public void setTotalExpensesPending(Double totalExpensesPending) {
        this.totalExpensesPending = totalExpensesPending;
    }

    public Double getTotalExpensesReceived() {
        return totalExpensesReceived;
    }

    public void setTotalExpensesReceived(Double totalExpensesReceived) {
        this.totalExpensesReceived = totalExpensesReceived;
    }

    public Double getTotalExpensesPendingByOther() {
        return totalExpensesPendingByOther;
    }

    public void setTotalExpensesPendingByOther(Double totalExpensesPendingByOther) {
        this.totalExpensesPendingByOther = totalExpensesPendingByOther;
    }

    private Double totalExpensesReceived = 0.00;
    private Double totalExpensesPendingByOther = 0.00;


    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", totalExpensesPending=" + totalExpensesPending +
                ", totalExpensesReceived=" + totalExpensesReceived +
                ", totalExpensesPendingByOther=" + totalExpensesPendingByOther +
                '}';
    }
}
