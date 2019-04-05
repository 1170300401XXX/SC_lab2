package P3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;


class PlayerTest {

    @Test public void testPlayer() {
        Player player1=new Player("yxt");
        player1.addPiece(new Piece(1, "yxt", 1, 1));
        player1.addPiece(new Piece(1, "yxt", 2, 2));
        player1.addHistory("what?");
        assertEquals("what?", player1.getGameHistoryBuilder());
        assertEquals("yxt", player1.getName());
        Set<Piece> pieces1=player1.getPlayerPieces();
        pieces1.add(new Piece(1, "yxt", 2, 2));
        pieces1.add(new Piece(1, "yxt", 1, 1));

        assertFalse(player1.addPiece(new Piece(1, "yxt", 1, 1)));
        
        assertEquals(pieces1, player1.getPlayerPieces());
        assertTrue(player1.isContainPiece(new Piece(1, "yxt", 1, 1)));
        assertFalse(player1.isContainPiece(new Piece(1, "yxt", 3, 2)));
        assertTrue(player1.removePieces(new Piece(1, "yxt", 1, 1)));
        assertFalse(player1.removePieces(new Piece(1, "yxt", 3, 2)));
    }
}
