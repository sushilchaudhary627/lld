package models;

import constants.SplitType;

public class ExactSplit extends Split{
    public ExactSplit(Integer splitId, User user, Double amount) {
        super(splitId, user, amount);
    }

    @Override
    public SplitType getSplitType() {
        return SplitType.EXACT;
    }
}
