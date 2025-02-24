package repository;

import model.TopicSubscribeDetail;

public interface SubscribeDetailRepository {
    public void save(TopicSubscribeDetail topicSubscribeDetail);
    public void update(TopicSubscribeDetail topicSubscribeDetail);
}
