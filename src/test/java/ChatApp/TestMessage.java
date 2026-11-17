package ChatApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestMessage {
    @Test
    public void testMessage() {
        User Hugo = new  User("Hugo");
        User Luisa = new  User("Luisa");

        Message message = new Message(Hugo,List.of(Luisa), "Hello Luisa!");

        assertSame(Hugo,message.getSender());
        assertEquals("Hello Luisa!", message.getContent());
        assertEquals(1,message.getRecipients().size());
        assertEquals(Luisa,message.getRecipients().get(0));

    }
}
