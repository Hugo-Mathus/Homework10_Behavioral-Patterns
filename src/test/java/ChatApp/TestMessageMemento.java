package ChatApp;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestMessageMemento {

    @Test
    public void testMementoCreation() {
        String content = "Hello Hugo!";
        LocalDateTime timestamp = LocalDateTime.now();

        MessageMemento memento = new MessageMemento(content, timestamp);

        assertEquals(content, memento.getContent());
        assertEquals(timestamp, memento.getTimestamp());
    }

}