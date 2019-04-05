package P3;

public class Game {

    private Board gameBoard;
    private Action gameAction;
    
    public void putDownPiece(Player player, Piece piece, Position pos) {
        gameAction.putPiece(player, piece, pos);
    }

    public void movePiece(Player player, Position s, Position t) {
        gameAction.movePiece(player, s, t);
    }

    public void removePiece(Player player, Position pos) {
        gameAction.removePiece(player, pos);
    }

    public void eatPiece(Player player, Position s, Position t) {
        gameAction.eatPiece(player, s, t);
    }

    public int getNumOfPlayerPiecesInBoard(Player player) {
        return gameBoard.getNumOfPlayerPiecesInBoard(player);
    }
    
    public Game(int gameType, Player player1, Player player2) {
        super();
        this.gameBoard = new Board(gameType);
        this.gameAction = new Action(player1, player2);
    }
}
