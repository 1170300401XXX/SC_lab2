package P3;

public class Board {
    
    public int getNumOfPlayerPiecesInBoard(Player player) {
        int count = 0;
        for (Piece piece : player.getPlayerPieces()) {
            if (piece.getPieceState() == 1)
                count++;
        }
        return count;
    }

    public Board(int boardType) {
        super();
    }

}
