
package ChatApp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUser {

    @Test
    public void testUndoLastMessage() {
        ChatServer server = new ChatServer();

        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);

        server.registerUser(hugo);
        server.registerUser(luisa);

        hugo.sendMessage(List.of(luisa), "Hello Luisa!");
        hugo.sendMessage(List.of(luisa), "How are you?");

        assertEquals(
                "How are you?",
                hugo.getChatHistory().getLastSentMessage().getContent()
        );

        hugo.undoLastMessage();

        assertEquals(
                "Hello Luisa!",
                hugo.getChatHistory().getLastSentMessage().getContent()
        );

    }
}