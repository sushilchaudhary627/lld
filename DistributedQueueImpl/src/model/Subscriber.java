package model;

public class Subscriber {
    private final Integer subscriberId;
    private String name;

    public Subscriber(Integer subscriberId, String name) {
        this.subscriberId = subscriberId;
        this.name = name;
    }

    public Integer getSubscriberId() {
        return subscriberId;
    }

    public String getName() {
        return name;
    }
}
