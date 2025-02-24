package workers;

import consumer.Consumer;
import model.Message;
import model.Topic;
import model.TopicSubscribeDetail;

public class SubscriptionWorker implements  Runnable{
    private final Topic topic;
    private final TopicSubscribeDetail topicSubscribeDetail;
    private final Consumer consumer;

    public SubscriptionWorker(Topic topic, TopicSubscribeDetail topicSubscribeDetail, Consumer consumer) {
        this.topic = topic;
        this.topicSubscribeDetail = topicSubscribeDetail;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            Message message;
            synchronized (topicSubscribeDetail) {
                while (topicSubscribeDetail.getOffset().get() >= topic.getMessages().size()) {
                    try {
                        topicSubscribeDetail.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                int currentPos = topicSubscribeDetail.getOffset().get();
                message = topic.getMessages().get(currentPos);
                consumer.consume(message);
                topicSubscribeDetail.getOffset().compareAndSet(currentPos, currentPos + 1);
            }
        }
    }


    public void wakeIfNeeded() {
        System.out.println("Wake up if you need");
        synchronized (topicSubscribeDetail){
            topicSubscribeDetail.notifyAll();
        }
    }
}
