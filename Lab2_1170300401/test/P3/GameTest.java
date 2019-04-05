package P3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class GameTest {

    @Test public void testGame() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Game game=new Game(1, player1, player2);
        Piece piece1 = new Piece(1, "player1", 1, 1);
        game.putDownPiece(player1, piece1, new Position(1, 1));
        game.removePiece(player2, new Position(1, 1));
        assertEquals("put piece:(2,2)", player1.getGameHistoryBuilder());
        assertEquals("remove piece: (2,2)", player2.getGameHistoryBuilder());
        Piece piece2 = new Piece(1, "player1", 2, 2);
        Piece piece3 = new Piece(1, "player2", 3, 3);
        player1.addPiece(piece2);
        player2.addPiece(piece3);
        game.movePiece(player1, new Position(2, 2), new Position(3, 4));
        game.eatPiece(player2, new Position(3, 3), new Position(3, 4));

        assertEquals("put piece:(2,2)\n" + "move piece: from (3,3) to (5,4)", player1.getGameHistoryBuilder());
        assertEquals("remove piece: (2,2)\n" + "eat piece: (4,4) eat (5,4)", player2.getGameHistoryBuilder());
        assertEquals(0, game.getNumOfPlayerPiecesInBoard(player1));
        assertEquals(1, game.getNumOfPlayerPiecesInBoard(player2));
    }

}
