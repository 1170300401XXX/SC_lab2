package P3;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private String name;
    private StringBuilder gameHistoryBuilder;
    private Set<Piece> playerPieces;// 玩家拥有的所有棋子

    // Abstraction function:
    // TODO playerPiece contains all player's pieces
    // TODO name represent the player's name
    // TODO gameHistoryBuilder: the action history
    // Representation invariant:
    // TODO name can't be null
    // TODO playerPieces >=0
    // Safety from rep exposure:
    // TODO All fields are private
    public String getName() {
        checkRep();
        return name;
    }

    @Override public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (!(obj instanceof Player))
            return false;
        Player player = (Player) obj;
        if (!(player.gameHistoryBuilder.toString().trim().equals(gameHistoryBuilder.toString().trim())
                && player.name.equals(name) && player.playerPieces.equals(playerPieces)))
            return false;
        return true;
    }

    public String getGameHistoryBuilder() {
        checkRep();
        return gameHistoryBuilder.toString().trim();
    }

    public Set<Piece> getPlayerPieces() {
        checkRep();
        return playerPieces;
    }

    public boolean removePieces(Piece piece) {
        for (Piece p : playerPieces) {
            if (p.getPosition().equals(piece.getPosition())) {
                checkRep();
                return getPlayerPieces().remove(p);
            }
        }
        checkRep();
        return false;
    }

    public void addHistory(String gameStep) {
        checkRep();
        this.gameHistoryBuilder.append(gameStep);
    }

    public boolean addPiece(Piece p) {
        for (Piece piece : getPlayerPieces()) {
            if (piece.equals(p)) {
                checkRep();
                return false;
            }
        }
        playerPieces.add(p);
        checkRep();
        return true;

    }

    public boolean isContainPiece(Piece p) {
        for (Piece piece : getPlayerPieces()) {
            if (piece.getPosition().equals(p.getPosition())) {
                checkRep();
                return true;
            }
        }
        checkRep();
        return false;
    }

    // TODO constructor
    public Player(String name) {
        super();
        this.name = name;
        this.gameHistoryBuilder = new StringBuilder();
        this.playerPieces = new HashSet<Piece>();
        checkRep();
    }

    // TODO checkRep
    private void checkRep() {
        assert name != null;
        assert playerPieces.size() >= 0;

    }
}
