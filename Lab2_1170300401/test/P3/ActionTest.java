package P3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ActionTest {

    @Test public void testAction() {
        // fail("Not yet implemented");
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Action action = new Action(player1, player2);
        Piece piece1 = new Piece(1, "player1", 1, 1);
        action.putPiece(player1, piece1, new Position(1, 1));
        action.removePiece(player2, new Position(1, 1));
        action.putPiece(player2, new Piece(1, "player2", 2, 2), new Position(2, 2));
        action.removePiece(player1, new Position(2, 2));
        Piece piece2 = new Piece(1, "player1", 2, 2);
        Piece piece3 = new Piece(1, "player2", 3, 3);
        Piece piece4 = new Piece(1, "player1", 5, 6);
        Piece piece5 = new Piece(1, "player2", 2, 5);
        player1.addPiece(piece2);
        player1.addPiece(piece4);
        player2.addPiece(piece3);
        player2.addPiece(piece5);
        action.movePiece(player1, new Position(2, 2), new Position(3, 4));
        action.eatPiece(player2, new Position(3, 3), new Position(3, 4));
        action.movePiece(player2, new Position(2, 5), new Position(2, 1));
        action.eatPiece(player1, new Position(5, 6), new Position(2, 1));
        //System.out.println(player1.getGameHistoryBuilder());
        //System.out.println(player2.getGameHistoryBuilder());
        assertEquals("put piece:(2,2)\n" + "remove piece: (3,3)\n" + "move piece: from (3,3) to (5,4)\n"
                + "eat piece: (7,6) eat (2,3)", player1.getGameHistoryBuilder());
        assertEquals("remove piece: (2,2)\n" + "put piece:(3,3)\n" + "eat piece: (4,4) eat (5,4)\n"
                + "move piece: from (6,3) to (2,3)", player2.getGameHistoryBuilder());
    }

}
