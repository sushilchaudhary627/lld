package consumer;

import models.Message;
import models.Topic;
import models.TopicConsumptionDetail;
import public_interface.ISubscriber;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Subscriber   implements ISubscriber {
   private final String name;
   private final String subscriberId;

    public Subscriber(String name, String subscriberId) {
        this.name = name;
        this.subscriberId = subscriberId;
    }

    @Override
    public String getId() {
        return subscriberId;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
            System.out.println(subscriberId + ": " + LocalDateTime.now() + ": "  +message.getContent() );
    }
    // here We keep workers to process messages mapped to Topic name
}


// One subscriber could subscribed to many topics right ?
//
