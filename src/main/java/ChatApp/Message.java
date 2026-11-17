package ChatApp;

import java.time.LocalDateTime;
import  java.util.ArrayList;
import  java.util.List;

public class Message {
    private User sender;
    private List<User> recipients;
    private LocalDateTime timestamp;
    private String content;

    public Message(User sender, List<User> recipients, String content) {
        this.sender = sender;
        this.recipients = recipients;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public MessageMemento createMemento() {
        return new MessageMemento(content, timestamp);
    }

    public void setContent(String content){
        this.content = content;
    }

    public void restore(MessageMemento memento){
        this.content = memento.getContent();
        this.timestamp = memento.getTimestamp();
    }
}
