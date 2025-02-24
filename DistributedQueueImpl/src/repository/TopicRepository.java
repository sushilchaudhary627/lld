package repository;

import model.Topic;

public interface TopicRepository {
    public void save(Topic topic);
    public void update(Topic topic);
}
