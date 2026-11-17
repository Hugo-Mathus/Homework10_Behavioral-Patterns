
package ChatApp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestChatServer {

    private static class TestRecipient extends User {
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


    @Test
    public void testMultipleRecipients() {
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
        TestRecipient Krystal = new TestRecipient("Krystal", server);

        server.registerUser(hugo);
        server.registerUser(luisa);
        server.registerUser(Krystal);

        hugo.sendMessage(List.of(luisa, Krystal), "Hello everyone!");

        assertNotNull(luisa.receivedMessage);
        assertNotNull(Krystal.receivedMessage);

        assertEquals("Hello everyone!", luisa.receivedMessage.getContent());

        assertEquals("Hello everyone!", Krystal.receivedMessage.getContent());

        assertSame(luisa.receivedMessage, Krystal.receivedMessage);
    }


    @Test
    public void testBlockUser() {
        ChatServer server = new ChatServer();

        User hugo = new User("Hugo", server);
        TestRecipient luisa = new TestRecipient("Luisa", server);
        TestRecipient Krystal = new TestRecipient("Krystal", server);

        server.registerUser(hugo);
        server.registerUser(luisa);
        server.registerUser(Krystal);

        // Luisa blocks Hugo
        server.blockUser(luisa, hugo);

        // Hugo sends a message to both recipients
        hugo.sendMessage(List.of(luisa, Krystal),"Hello everyone!"
        );

        assertNull(luisa.receivedMessage);
        assertNotNull(Krystal.receivedMessage);
        assertEquals("Hello everyone!", Krystal.receivedMessage.getContent()
        );
    }

}
