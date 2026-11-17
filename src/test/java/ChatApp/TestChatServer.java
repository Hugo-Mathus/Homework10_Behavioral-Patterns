
package ChatApp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestChatServer {


    @Test
    public void testSendMessage() {
        ChatServer server = new ChatServer();

        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);

        server.registerUser(hugo);
        server.registerUser(luisa);

        hugo.sendMessage(List.of(luisa), "Hello Luisa!");

        Message sent = hugo.getChatHistory().getLastSentMessage();
        System.out.println("Last sent message: " + hugo.getChatHistory().getLastSentMessage());
        assertNotNull(sent);
        assertEquals("Hello Luisa!", sent.getContent());
    }

}
