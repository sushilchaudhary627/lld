package services;

import constants.SplitStatus;
import constants.SplitType;
import factory.SplitFactory;
import models.Split;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SplitService {
    private final AtomicInteger splitIdGenerator ;
    private final UserService userService;

    public SplitService(AtomicInteger splitIdGenerator, UserService userService) {
        this.splitIdGenerator = splitIdGenerator;
        this.userService = userService;
    }

    public List<Split> createSplit(Map<Integer, Double> splitDetails, Double totalAmount, SplitType splitType) {
        List<Split>splits = new ArrayList<>();
        for(Map.Entry<Integer, Double> entry: splitDetails.entrySet()){
            User user = userService.findUserById(entry.getKey());
            Split split = SplitFactory.createSplit(
                splitIdGenerator.getAndIncrement(), user, splitType, entry.getValue(), totalAmount);
            split.setSplitStatus(SplitStatus.PENDING);
            splits.add(split);

        }
        return splits;
    }

    public Split markPaid(Split split){
        split.setSplitStatus(SplitStatus.PAID);
        return split;
    }
}
