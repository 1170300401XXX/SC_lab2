package P3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class PieceTest {

   
    @Test public void testPiece() {
        Piece piece1=new Piece(1, "yxt", 2, 3);
        Piece piece2=new Piece(1, "yxt", 1, 2);
        
        assertEquals(1, piece1.getPieceState());
        assertEquals(2, piece1.getPx());
        assertEquals(3, piece1.getPy());
        assertEquals("yxt", piece1.getpName());
        assertFalse(piece1.equals(3));
        assertEquals(new Position(1, 2), piece2.getPosition());
    }
}
