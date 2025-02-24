package model;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscribeDetail {
    private final Subscriber subscriber;
    private final AtomicInteger offset;

    public TopicSubscribeDetail(Subscriber subscriber, AtomicInteger offset) {
        this.subscriber = subscriber;
        this.offset = offset;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public AtomicInteger getOffset() {
        return offset;
    }
}
