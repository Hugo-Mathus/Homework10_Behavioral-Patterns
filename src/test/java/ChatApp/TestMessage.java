package ChatApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestMessage {
    @Test
    public void testMessage() {
        ChatServer server = new ChatServer();
        User Hugo = new  User("Hugo",server);
        User Luisa = new  User("Luisa",server);

        Message message = new Message(Hugo,List.of(Luisa), "Hello Luisa!");

        assertSame(Hugo,message.getSender());
        assertEquals("Hello Luisa!", message.getContent());
        assertEquals(1,message.getRecipients().size());
        assertEquals(Luisa,message.getRecipients().get(0));
    }

    @Test
    public void testTimestamp() {
        ChatServer server = new ChatServer();
        User hugo = new User("Hugo",server);
        User luisa = new User("Luisa",server);
        Message message = new Message(hugo, List.of(luisa), "Hello Luisa!");

        MessageMemento memento = message.createMemento();

        assertEquals("Hello Luisa!", memento.getContent());
        assertEquals(message.getTimestamp(), memento.getTimestamp());

    }


    @Test
    public void testRestoreMemento() {
        ChatServer server = new ChatServer();
        User hugo = new User("Hugo",server);
        User luisa = new User("Luisa",server);
        Message message = new Message(hugo, List.of(luisa), "Hello Luisa!");
        MessageMemento memento = message.createMemento();

        message.setContent("Goodbye Luisa!");
        assertEquals("Goodbye Luisa!", message.getContent());

        message.restore(memento);
        assertEquals("Hello Luisa!", message.getContent());
        assertEquals(memento.getTimestamp(), message.getTimestamp());
    }

}
