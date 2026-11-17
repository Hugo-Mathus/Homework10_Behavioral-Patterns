package ChatApp;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private final User owner;
    private List<Message> messages;

    public ChatHistory(User owner) {
        this.owner = owner;
        this.messages = new ArrayList<>();
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        return new searchMessagesByUser(messages, userToSearchWith);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getLastSentMessage() {
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if (message.getSender() == owner) {
                return message;
            }
        }
        return null;
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public void displayHistory() {
        for (Message message : messages) {
            System.out.println(message.getSender().getUsername() + ": " + message.getContent());
        }
    }

}
