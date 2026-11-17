
package ChatApp;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSearchMessagesByUser {

    @Test
    public void testIteratorByUser() {
        ChatServer server = new ChatServer();

        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);
        User krystal = new User("Krystal", server);

        ChatHistory history = new ChatHistory(hugo);
        Message message1 = new Message(hugo, List.of(luisa), "Hello Luisa!");
        Message message2 = new Message(krystal, List.of(hugo), "Hi Hugo!");
        Message message3 = new Message(luisa, List.of(hugo), "How are you?");

        history.addMessage(message1);
        history.addMessage(message2);
        history.addMessage(message3);

        Iterator<Message> iterator = history.iterator(luisa);

        assertTrue(iterator.hasNext());
        assertSame(message1, iterator.next());

        assertTrue(iterator.hasNext());
        assertSame(message3, iterator.next());

        assertFalse(iterator.hasNext());
    }
}
