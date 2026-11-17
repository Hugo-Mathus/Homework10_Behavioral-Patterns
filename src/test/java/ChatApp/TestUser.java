
package ChatApp;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class TestUser {

    @Test
    public void testUndoLastMessage() {
        ChatServer server = new ChatServer();

        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);

        server.registerUser(hugo);
        server.registerUser(luisa);

        // Hugo sends two messages
        hugo.sendMessage(List.of(luisa), "Hello Luisa!");
        hugo.sendMessage(List.of(luisa), "How are you?");

        // Verify the last message before undo
        Message lastMessage =
                hugo.getChatHistory().getLastSentMessage();

        assertNotNull(lastMessage);
        assertEquals("How are you?", lastMessage.getContent());

        // Hugo undoes his last message
        hugo.undoLastMessage();

        // Verify the previous message is still in history
        Message afterUndo =
                hugo.getChatHistory().getLastSentMessage();

        assertNotNull(afterUndo);
        assertEquals("Hello Luisa!", afterUndo.getContent());
    }

    @Test
    public void testUndoWithNoMessages() {
        ChatServer server = new ChatServer();
        User hugo = new User("Hugo", server);

        server.registerUser(hugo);

        assertDoesNotThrow(() -> hugo.undoLastMessage());
        assertNull(hugo.getChatHistory().getLastSentMessage());
    }


    @Test
    public void testUserIterator() {
        ChatServer server = new ChatServer();

        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);
        User krystal = new User("Krystal", server);

        server.registerUser(hugo);
        server.registerUser(luisa);
        server.registerUser(krystal);

        hugo.sendMessage(List.of(luisa), "Hello Luisa!");
        hugo.sendMessage(List.of(krystal), "Hello Krystal!");
        luisa.sendMessage(List.of(hugo), "Hi Hugo!");

        Iterator<Message> iterator = hugo.iterator(luisa);

        assertTrue(iterator.hasNext());
        assertEquals("Hello Luisa!", iterator.next().getContent());

        assertTrue(iterator.hasNext());
        assertEquals("Hi Hugo!", iterator.next().getContent());

        assertFalse(iterator.hasNext());
    }

}
