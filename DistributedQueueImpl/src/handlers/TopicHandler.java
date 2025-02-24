package handlers;

import consumer.impl.ConsumerImpl;
import model.Subscriber;
import model.Topic;
import model.TopicSubscribeDetail;
import workers.SubscriptionWorker;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

public class TopicHandler {
    private final Topic topic;
    private final ConcurrentMap<Integer, SubscriptionWorker> subscriptionWorkerMap;
    private final ExecutorService executorService;
    public TopicHandler(Topic topic, ConcurrentMap<Integer, SubscriptionWorker> subscriptionWorkerMap, ExecutorService executorService) {
        this.topic = topic;
        this.subscriptionWorkerMap = subscriptionWorkerMap;
        this.executorService = executorService;
    }

    public void publish(){
        for(TopicSubscribeDetail topicSubscribeDetail: topic.getTopicSubscribeDetails()){
            notifyWorker(topicSubscribeDetail);
        }
    }

    public void notifyWorker(TopicSubscribeDetail topicSubscribeDetail){
           subscriptionWorkerMap.computeIfAbsent(topicSubscribeDetail.getSubscriber().getSubscriberId(), k -> {
               SubscriptionWorker subscriptionWorker = new SubscriptionWorker(topic, topicSubscribeDetail, new ConsumerImpl(topicSubscribeDetail.getSubscriber()));
               executorService.submit(subscriptionWorker);
               return subscriptionWorker;
           }).wakeIfNeeded();
    }
}


// state machine
