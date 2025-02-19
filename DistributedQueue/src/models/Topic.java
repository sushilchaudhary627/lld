package models;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    List<Message> messages;
    final String topicName;
    final Integer topicId;
    private final List<TopicConsumptionDetail> subscriberDetails;


    public Topic(String topicName, Integer topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.subscriberDetails = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public synchronized void addMessage(final Message message) {
        messages.add(message);
    }

    public void addSubscriber(final TopicConsumptionDetail subscriber) {
        subscriberDetails.add(subscriber);
    }

    public List<TopicConsumptionDetail> getSubscriberDetails() {
        return subscriberDetails;
    }

    public List<Message> getMessages(){
        return messages;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "messages=" + messages +
                ", topicName='" + topicName + '\'' +
                ", topicId=" + topicId +
                ", subscriberDetails=" + subscriberDetails +
                '}';
    }
}
