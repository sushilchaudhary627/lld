package services;
import model.*;
import handlers.TopicHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicService {
    private final AtomicInteger idGenerator;
    final Map<Integer, TopicHandler> topicHandlerMap;

    public TopicService(AtomicInteger idGenerator, Map<Integer, TopicHandler> topicHandlerMap) {
        this.idGenerator = idGenerator;
        this.topicHandlerMap = topicHandlerMap;
    }

    public Topic createNewTopic(String name){
        Topic topic = new Topic(idGenerator.getAndIncrement(), name);
        topicHandlerMap.put(topic.getTopicId(), new TopicHandler(topic, new ConcurrentHashMap<>(), Executors.newFixedThreadPool(2)));
        return topic;
    }

    public void publishMessage(Topic topic, Message message){
        topic.addMessage(message);
        topicHandlerMap.get(topic.getTopicId()).publish();
    }

    public void addSubscriberToTopic(Topic topic, Subscriber subscriber){
        topic.addNewSubscribeDetail(new TopicSubscribeDetail(subscriber, new AtomicInteger(0)));
    }
}
