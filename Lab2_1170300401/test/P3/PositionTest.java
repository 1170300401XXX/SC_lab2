package P3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test public void testPosition() {
        Position pos = new Position(1, 2);
        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
        Position pos2 = new Position(1, 2);
        assertTrue(pos.equals(pos2));
        assertFalse(pos.equals(2));
        assertFalse(pos.equals(new Position(2, 3)));
    }
}
