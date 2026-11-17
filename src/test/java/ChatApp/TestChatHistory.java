package ChatApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class TestChatHistory {

    @Test
    public void testAddMessage() {
        ChatServer server = new ChatServer();
        User hugo = new User("Hugo",server);
        User luisa = new User("Luisa",server);

        ChatHistory history = new ChatHistory(hugo);

        Message message = new Message(hugo,List.of(luisa),"Hello Luisa!");

    }

}
