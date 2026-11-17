package ChatApp;

import java.util.List;
import java.util.ArrayList;

public class ChatHistory {
    private final User owner;
    private List<Message> messages;

    public ChatHistory(User owner) {
        this.owner = owner;
        this.messages = new ArrayList<>();
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
}
