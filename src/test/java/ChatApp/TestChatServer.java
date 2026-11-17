
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
        TestRecipient bob = new TestRecipient("Bob", server);

        server.registerUser(hugo);
        server.registerUser(luisa);
        server.registerUser(bob);

        hugo.sendMessage(List.of(luisa, bob), "Hello everyone!");

        assertNotNull(luisa.receivedMessage);
        assertNotNull(bob.receivedMessage);

        assertEquals("Hello everyone!", luisa.receivedMessage.getContent());

        assertEquals("Hello everyone!", bob.receivedMessage.getContent());

        assertSame(luisa.receivedMessage, bob.receivedMessage);
    }


    @Test
    public void testBlockUser() {
        ChatServer server = new ChatServer();
        User hugo = new User("Hugo", server);
        TestRecipient luisa = new TestRecipient("Luisa", server);
        TestRecipient bob = new TestRecipient("Bob", server);

        server.registerUser(hugo);
        server.registerUser(luisa);
        server.registerUser(bob);

        // Luisa blocks Hugo
        server.blockUser(luisa, hugo);

        // Hugo sends a message to both users
        hugo.sendMessage(
                List.of(luisa, bob),
                "Hello everyone!"
        );

        // Luisa should not receive it
        assertNull(luisa.receivedMessage);

        // Bob should receive it
        assertNotNull(bob.receivedMessage);
        assertEquals(
                "Hello everyone!",
                bob.receivedMessage.getContent()
        );
    }

}
