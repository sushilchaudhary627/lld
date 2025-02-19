package services;

import models.*;
import processor.TopicHandler;
import public_interface.ISubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class TopicService {
    AtomicInteger idGenerator = new AtomicInteger(1);
    Map<Topic, TopicHandler> topicHandlerMap = new HashMap<>();
    public Topic createTopic(String topicName){
        Topic topic =new Topic(topicName, idGenerator.getAndIncrement());
        topicHandlerMap.put(topic, new TopicHandler(topic));
        return topic;
    }

    public void publishMessage(Topic topic, Message message){
        topic.addMessage(message);
        topicHandlerMap.get(topic).publish();
    }

    public void addConsumer(Topic topic, ISubscriber consumer){
        topic.addSubscriber(new TopicConsumptionDetail(consumer));
        topicHandlerMap.get(topic).publish();
    }
}
