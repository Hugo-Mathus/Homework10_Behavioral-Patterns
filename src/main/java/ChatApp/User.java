package ChatApp;

public class User {
    private String username;
    private Mediator mediator;

    public User(String username){
            this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
