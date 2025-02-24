package models;

import public_interface.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicConsumptionDetail {
    private final AtomicInteger offset;
    private final ISubscriber subscriber;

    public TopicConsumptionDetail(final ISubscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public AtomicInteger getOffset(){
        return offset;
    }
}
