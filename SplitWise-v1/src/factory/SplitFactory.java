package factory;

import constants.SplitType;
import models.*;

public class SplitFactory {
   public static  Split createSplit(Integer id, User user, SplitType splitType, Double distributionValue, Double totalAmount){
        switch(splitType){
            case SplitType.PERCENTAGE -> {
                return new PercentageSplit(id, user, totalAmount*distributionValue/100, distributionValue);
            }
            case SplitType.EQUAL -> {
                return new EqualSplit(id, user, distributionValue);
            }
            case SplitType.EXACT -> {
                return new ExactSplit(id, user, distributionValue);
            }
            default -> {
                throw new RuntimeException("Split type is not defined");
            }
        }
    }
}
