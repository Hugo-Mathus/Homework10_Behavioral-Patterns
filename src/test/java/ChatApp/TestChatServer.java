
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



    @Test
    public void testRecipientReceivesMessage() {
        ChatServer server = new ChatServer();

        User hugo = new User("Hugo", server);

        class TestRecipient extends User {
            Message receivedMessage;

            TestRecipient(String username, Mediator mediator) {
                super(username, mediator);
            }

            @Override
            public void receiveMessage(Message message) {
                super.receiveMessage(message);
                receivedMessage = message;
            }
        }

        TestRecipient luisa = new TestRecipient("Luisa", server);

        server.registerUser(hugo);
        server.registerUser(luisa);

        hugo.sendMessage(List.of(luisa), "Hello Luisa!");

        assertNotNull(luisa.receivedMessage);
        assertEquals("Hello Luisa!", luisa.receivedMessage.getContent());
    }



}
