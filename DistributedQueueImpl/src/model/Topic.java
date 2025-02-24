package model;
import java.util.*;

public class Topic {
    private Integer topicId;
    private String topicName;
    private List<Message>messages;
    private List<TopicSubscribeDetail>topicSubscribeDetails;

    public Topic(Integer topicId, String topicName){
        this.topicId = topicId;
        this.topicName = topicName;
        this.messages = new ArrayList<>();
        this.topicSubscribeDetails = new ArrayList<>();
    }


    public void addMessage(Message message){
        messages.add(message);
    }

    public void addNewSubscribeDetail(TopicSubscribeDetail topicSubscribeDetail){
        topicSubscribeDetails.add(topicSubscribeDetail);
    }

    public Integer getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<TopicSubscribeDetail> getTopicSubscribeDetails() {
        return topicSubscribeDetails;
    }
}
