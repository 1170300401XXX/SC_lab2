package P3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test public void testBoard() {
        Board board=new Board(1);
        Player player=new Player("yxt");
        player.addPiece(new Piece(1, "yxt", 1, 1));
        assertEquals(1, board.getNumOfPlayerPiecesInBoard(player));
    }
}
