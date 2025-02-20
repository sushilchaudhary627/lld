import model.*;
import services.TopicService;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TopicService topicService = new TopicService(new AtomicInteger(1), new HashMap<>());
        Topic topic = topicService.createNewTopic("Order update");
        topicService.addSubscriberToTopic(topic, new Subscriber(1, "order service"));
        topicService.addSubscriberToTopic(topic, new Subscriber(2, "metric service"));
        topicService.publishMessage(topic, new Message(1, "Order is dispatched"));
        topicService.publishMessage(topic, new Message(2, "Order reached to nearest location"));
        topicService.publishMessage(topic, new Message(3, "Customer refused"));
        topicService.publishMessage(topic, new Message(4, "Order is delivered"));
    }
}