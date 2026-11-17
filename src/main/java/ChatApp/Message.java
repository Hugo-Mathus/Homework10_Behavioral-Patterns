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
        return null;
    }

    public List<User> getRecipients() {
        return null;
    }

    public String getContent() {
        return null;
    }

}
