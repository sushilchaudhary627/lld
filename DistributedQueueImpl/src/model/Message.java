package model;

public class Message {
    private final Integer messageId;
    private final String content;

    public Message(Integer messageId, String content) {
        this.messageId = messageId;
        this.content = content;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }
}
