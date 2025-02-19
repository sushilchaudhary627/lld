package public_interface;

import models.Message;

public interface ISubscriber {

    String getId();
    void consume(Message message) throws InterruptedException;
}