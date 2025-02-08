package models;

public class Split {
    Integer splitId;
    User user;
    SplitStatus splitStatus;
    Double amount;

    public Split(Integer splitId, User user, SplitStatus splitStatus, Double amount) {
        this.splitId = splitId;
        this.user = user;
        this.splitStatus = splitStatus;
        this.amount = amount;
    }

    public void setSplitStatus(SplitStatus splitStatus) {
        this.splitStatus = splitStatus;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public User getUser() {
        return user;
    }

    public SplitStatus getSplitStatus() {
        return splitStatus;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Split{" +
                "splitId=" + splitId +
                ", user=" + user +
                ", splitStatus=" + splitStatus +
                ", amount=" + amount +
                '}';
    }
}
