import consumer.Subscriber;
import models.*;
import services.TopicService;

import java.util.function.Consumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TopicService topicService = new TopicService();
        Topic topic = topicService.createTopic("Test me");
        System.out.println(topic);
        topicService.publishMessage(topic, new Message("ok lollll"));
        System.out.println(topic);
        topicService.addConsumer(topic, new Subscriber("Hey", "1"));
        topicService.addConsumer(topic, new Subscriber("Hey", "2"));
        topicService.addConsumer(topic, new Subscriber("Hey", "3"));
        topicService.addConsumer(topic, new Subscriber("Hey", "4"));
        topicService.publishMessage(topic, new Message("new new new"));
    }
}


// Topic contains msg
// N Producer
//M consumer for consuming msg
// need to remember