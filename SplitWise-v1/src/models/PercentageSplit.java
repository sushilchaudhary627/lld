package models;

import constants.SplitType;

public class PercentageSplit extends  Split{
    Double percentage;
    public PercentageSplit(Integer splitId, User user, Double amount, Double percentage) {
        super(splitId, user, amount);
        this.percentage = percentage;
    }

    @Override
    public SplitType getSplitType() {
        return SplitType.PERCENTAGE;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
