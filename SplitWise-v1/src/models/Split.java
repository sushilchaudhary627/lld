package models;

import constants.SplitStatus;
import constants.SplitType;

public abstract class Split {
    private final Integer splitId;
    private final User user;
    private Double amount;
    private SplitStatus splitStatus;

    public Split(Integer splitId, User user, Double amount) {
        this.splitId = splitId;
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public SplitStatus getSplitStatus() {
        return splitStatus;
    }

    public void setSplitStatus(SplitStatus splitStatus) {
        this.splitStatus = splitStatus;
    }

    public boolean isPending(){
        return splitStatus == SplitStatus.PENDING;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public abstract SplitType getSplitType();

    @Override
    public String toString() {
        return "\nSplit{" +
            "splitId=" + splitId +
            ", user=" + user.getName() +
            ", amount=" + amount +
            ", splitStatus=" + splitStatus +
            '}';
    }
}
