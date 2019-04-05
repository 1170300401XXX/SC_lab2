package P3;

public class Action {

    private Player playerA, playerB;
    // Abstraction function:
    // TODO represent two players' all information in the board
    // Representation invariant:
    // TODO AF(playerA)={playerA' pieces' information}
    // TODO AF(playerB)={playerA' pieces' information}

    // Safety from rep exposure:
    // TODO All fields are private
    // TODO checkRep
    private void checkRep() {
        Player checkPlayer1=new Player(playerA.getName());
        Player checkPlayer2=new Player(playerB.getName());
        checkPlayer1.addHistory(playerA.getGameHistoryBuilder());
        checkPlayer2.addHistory(playerB.getGameHistoryBuilder());
        for(Piece p: playerA.getPlayerPieces())
        checkPlayer1.addPiece(p);
        for(Piece p: playerB.getPlayerPieces())
            checkPlayer2.addPiece(p);
        assert playerA.equals(checkPlayer1)&&playerB.equals(checkPlayer2);
    }
    public void putPiece(Player player, Piece piece, Position pos) {
        player.addPiece(piece);
        checkRep();
        player.addHistory("put piece:" + "(" + (pos.getX() + 1) + "," + (pos.getY() + 1) + ")" + "\n");
    }

    public void movePiece(Player player, Position s, Position t) {
        if (player.getName().equals(playerA.getName())) {
            for (Piece piece : player.getPlayerPieces()) {
                if (piece.getPx() == s.getX() && piece.getPy() == s.getY()) {
                    playerA.removePieces(piece);
                    checkRep();
                    playerA.addPiece(new Piece(1, player.getName(), t.getX(), t.getY()));
                    break;
                }
            }
        } else {
            for (Piece piece : player.getPlayerPieces()) {
                if (piece.getPosition().getX() == s.getX() && piece.getPy() == s.getY()) {
                    playerB.removePieces(piece);
                    checkRep();
                    playerB.addPiece(new Piece(1, player.getName(), t.getX(), t.getY()));
                    break;
                }
            }
        }
        player.addHistory("move piece: from (" + (s.getY() + 1) + "," + (s.getX() + 1) + ") to (" + (t.getY() + 1)
                + "," + (t.getX() + 1) + ")" + "\n");
    }

    public void removePiece(Player player, Position pos) {
        if (playerA.getName().equals(player.getName())) {
            for (Piece piece : playerB.getPlayerPieces()) {
                if (piece.getPosition().equals(pos)) {
                    playerB.removePieces(piece);
                    checkRep();
                    break;
                }
            }
        } else {
            for (Piece piece : playerA.getPlayerPieces()) {
                if (piece.getPosition().equals(pos)) {
                    playerA.removePieces(piece);
                    checkRep();
                    break;
                }
            }
        }
        player.addHistory("remove piece: (" + (pos.getX()+1) + "," + (pos.getY()+1) + ")" + "\n");
    }

    public void eatPiece(Player player, Position s, Position t) {
        if (playerA.getName().equals(player.getName())) {
            for (Piece piece : playerB.getPlayerPieces()) {
                if (piece.getPosition().equals(t)) {
                    playerB.removePieces(piece);
                    break;
                }
            }
            for (Piece piece : playerA.getPlayerPieces()) {
                if (piece.getPosition().equals(s)) {
                    playerA.removePieces(piece);
                    playerA.addPiece(new Piece(1, playerA.getName(), t.getX(), t.getY()));
                    break;
                }
            }
            checkRep();
        } else {
            for (Piece piece : playerA.getPlayerPieces()) {
                if (piece.getPosition().equals(t)) {
                    playerA.removePieces(piece);
                    break;
                }
            }
            for (Piece piece : playerB.getPlayerPieces()) {
                if (piece.getPosition().equals(s)) {
                    playerB.removePieces(piece);
                    playerB.addPiece(new Piece(1, playerB.getName(), t.getX(), t.getY()));
                    break;
                }
            }
            checkRep();
        }
        player.addHistory("eat piece: (" + (s.getY() + 1) + "," + (s.getX() + 1) + ") eat (" + (t.getY() + 1) + ","
                + (t.getX() + 1) + ")\n");

    }
    // TODO constructor
    public Action(Player player1, Player player2) {
        super();
        this.playerA = player1;
        this.playerB = player2;
        checkRep();
    }

}
