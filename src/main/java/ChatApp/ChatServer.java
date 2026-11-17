package ChatApp;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class ChatServer implements Mediator {
    private List<User> users = new ArrayList<>();

    public ChatServer() {
        users = new ArrayList<>();
    }

    @Override
    public void registerUser(User user) {
        if (user != null && !users.contains(user)) {
            users.add(user);
        }
    }
    @Override
    public void unregisterUser(User user) {
        users.remove(user);
    }

    @Override
    public void sendMessage(Message message){
        User sender = message.getSender();
        if (!users.contains(sender)) {
            return;
        }
        boolean delivered= false;
        for (User recipient : message.getRecipients()) {
            if (users.contains(recipient)) {
                recipient.receiveMessage(message);
                delivered = true;
            }
        }
        if (delivered) {
            sender.receiveMessage(message);
        }
    }

    @Override
    public void blockUser(User user, User blockeUser) {

    }

}
