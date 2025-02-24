package models;

import constants.SplitType;

public class EqualSplit extends Split{
    public EqualSplit(Integer splitId, User user, Double amount) {
        super(splitId, user, amount);
    }

    @Override
    public SplitType getSplitType() {
        return SplitType.EQUAL;
    }
}
