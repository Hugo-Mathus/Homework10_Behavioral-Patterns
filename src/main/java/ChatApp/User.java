package ChatApp;

import  java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private Mediator mediator;
    private ChatHistory history;

    public User(String username, Mediator mediator) {
            this.username = username;
            this.mediator = mediator;
            this.history = new ChatHistory(this);
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(List<User> recipients, String content) {
        Message message = new Message(this, recipients, content);
        mediator.sendMessage(message);
    }
}
