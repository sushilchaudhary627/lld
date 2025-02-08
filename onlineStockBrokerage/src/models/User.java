package models;

public class User {
    private Integer userId;
    private String name;
    private String mobile;
    private Double balance;

    public User(Integer userId, String name, String mobile) {
        this.userId = userId;
        this.name = name;
        this.mobile = mobile;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", balance=" + balance +
                '}';
    }
}
