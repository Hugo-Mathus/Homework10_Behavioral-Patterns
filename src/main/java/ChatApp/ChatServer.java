package ChatApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class ChatServer implements Mediator {
    private List<User> users = new ArrayList<>();
    private Map<User, List<User>> blockList = new HashMap<>();
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

            if (!users.contains(recipient)) {
                continue;
            }

            if (isBlocked(recipient, sender)) {
                continue;
            }

            recipient.receiveMessage(message);
            delivered = true;
        }
        if (delivered) {
            sender.receiveMessage(message);
        }
    }

    @Override
    public void blockUser(User user, User blockedUser) {
        if (!blockList.containsKey(user)) {
            blockList.put(user, new ArrayList<>());
        }

        List<User> blockedUsers = blockList.get(user);

        if (!blockedUsers.contains(blockedUser)) {
            blockedUsers.add(blockedUser);
        }
    }

    private boolean isBlocked(User recipient, User sender) {
        if (!blockList.containsKey(recipient)) {
            return false;
        }

        return blockList.get(recipient).contains(sender);
    }

    @Override
    public void undoMessage(Message message) {
        User sender = message.getSender();

        sender.getChatHistory().removeMessage(message);

        for (User recipient : message.getRecipients()) {
            recipient.getChatHistory().removeMessage(message);
        }
    }


}
