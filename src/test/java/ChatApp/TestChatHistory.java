package ChatApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class TestChatHistory {


    @Test
    public void testAddMessage() {
        ChatServer server = new ChatServer();
        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);

        ChatHistory history = new ChatHistory(hugo);

        Message message = new Message(
                hugo, List.of(luisa), "Hello Luisa!"
        );

        history.addMessage(message);

        assertSame(message, history.getLastSentMessage());
    }


    @Test
    public void testGetLastSentMessage() {
        ChatServer server = new ChatServer();
        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);

        ChatHistory history = new ChatHistory(hugo);

        Message message1 = new Message(hugo, List.of(luisa), "Hello Luisa!");

        Message message2 = new Message(luisa, List.of(hugo), "Hi Hugo!");

        Message message3 = new Message(hugo, List.of(luisa), "How are you?");

        Message message4 = new Message(luisa, List.of(hugo), "I'm good!");

        history.addMessage(message1);
        history.addMessage(message2);
        history.addMessage(message3);
        history.addMessage(message4);

        assertSame(message3, history.getLastSentMessage());
    }


}
