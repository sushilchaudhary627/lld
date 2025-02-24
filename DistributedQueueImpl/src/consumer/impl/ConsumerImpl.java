package consumer.impl;

import consumer.Consumer;
import model.Message;
import model.Subscriber;

public class ConsumerImpl implements Consumer {
    private final Subscriber subscriber;

    public ConsumerImpl(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Integer getConsumerId() {
        return subscriber.getSubscriberId();
    }

    @Override
    public void consume(Message message) {
        System.out.println("Started consuming consumer id: " + subscriber.getSubscriberId() + " message: "+message.getContent() );
    }
}
