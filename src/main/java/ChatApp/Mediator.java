package ChatApp;

public interface Mediator {
    void registerUser(User user);
    void unregisterUser(User user);
    void sendMessage(Message message);
    void blockUser(User user, User blockeUser);
}
