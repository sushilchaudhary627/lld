package workers;

import models.Message;
import models.Topic;
import models.TopicConsumptionDetail;

public class SubscriptionWorker implements Runnable {
    private final Topic topic;
    private final TopicConsumptionDetail topicConsumptionDetail;

    public SubscriptionWorker(Topic topic, TopicConsumptionDetail topicConsumptionDetail) {
        this.topic = topic;
        this.topicConsumptionDetail = topicConsumptionDetail;
    }

    @Override
    public void run() {
        synchronized (topicConsumptionDetail) {
            do {
                int currentPos = topicConsumptionDetail.getOffset().get();
                while (currentPos >= topic.getMessages().size()) {
                    try {
                        topicConsumptionDetail.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Message message = topic.getMessages().get(currentPos);
                try {
                    topicConsumptionDetail.getSubscriber().consume(message);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                topicConsumptionDetail.getOffset().compareAndSet(currentPos, currentPos + 1);
            }
            while (topicConsumptionDetail.getOffset().get() < topic.getMessages().size());
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicConsumptionDetail) {
            topicConsumptionDetail.notifyAll();
        }
    }
}


//
