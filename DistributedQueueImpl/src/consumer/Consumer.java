package consumer;

import model.Message;

public interface Consumer {
    public Integer getConsumerId();
    public void consume(Message message);
}
