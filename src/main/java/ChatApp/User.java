package ChatApp;

import  java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private Mediator mediator;
    private ChatHistory history;
    private Message lastSentMessage;
    private MessageMemento lastMemento;

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

        if (history.getLastSentMessage() == message) {
            lastSentMessage = message;
            lastMemento = message.createMemento();
        }
    }

    public void undoLastMessage() {
        if (lastSentMessage == null || lastMemento == null) {
            return;
        }
        lastSentMessage.restore(lastMemento);
        mediator.undoMessage(lastSentMessage);

        lastSentMessage = null;
        lastMemento = null;
    }

    public void receiveMessage(Message message) {
        history.addMessage(message);
    }

    public ChatHistory getChatHistory() {
        return history;
    }
}
