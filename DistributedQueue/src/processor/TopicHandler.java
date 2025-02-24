package processor;

import models.Topic;
import models.TopicConsumptionDetail;
import workers.SubscriptionWorker;

import java.util.concurrent.*;

public class TopicHandler {
    private final Topic topic;
    private final ConcurrentHashMap<String, SubscriptionWorker> subscriberWorkers;
    private final ExecutorService executorService;

    public TopicHandler(Topic topic) {
        this.topic = topic;
        this.subscriberWorkers = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(5); // Configurable pool size
    }

    /**
     * Publishes messages to all subscribers by starting their workers.
     */
    public void publish() {
        for (TopicConsumptionDetail consumptionDetail : topic.getSubscriberDetails()) {
            startSubscriberWorker(consumptionDetail);
        }
    }

    /**
     * Starts a subscriber worker if not already running, and wakes it up if needed.
     */
    public void startSubscriberWorker(TopicConsumptionDetail topicConsumptionDetail) {
        String subscriberId = topicConsumptionDetail.getSubscriber().getId();

        // Ensure only one worker per subscriber is created
        subscriberWorkers.computeIfAbsent(subscriberId, id -> {
            SubscriptionWorker worker = new SubscriptionWorker(topic, topicConsumptionDetail);
            executorService.submit(worker); // Run in thread pool
            return worker;
        }).wakeUpIfNeeded(); // Wake up worker safely
    }

    /**
     * Gracefully shuts down the executor service.
     */
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
