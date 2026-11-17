package ChatApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestMessage {
    @Test
    public void testUser() {
        User alice = new  User("Alice");
        assertEquals("Alice", alice.getUsername());
    }
}
