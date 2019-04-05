package P3;

public class Piece {

    private int pieceState;// 0:未放置 1:已放置
    private String pName;// 棋子种类
    private Position piece;// 棋子位置
    // Abstraction function:
    // TODO piece represent the piece's position
    // TODO pName represent the owner of the piece's name
    // TODO pieceState: the piece is in the board or not
    // Representation invariant:
    // TODO pName and piece can't be null
    // TODO pieceState must be 0 or 1
    // Safety from rep exposure:
    // TODO All fields are private

    public int getPieceState() {
        checkRep();
        return pieceState;
    }

    public String getpName() {
        checkRep();
        return pName;
    }

    public Position getPosition() {
        checkRep();
        return this.piece;
    }

    public int getPx() {
        checkRep();
        return getPosition().getX();
    }

    public int getPy() {
        checkRep();
        return getPosition().getY();
    }

    @Override public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (!(obj instanceof Piece))
            return false;
        Piece p = (Piece) obj;
        if (p.pieceState != pieceState || !p.piece.equals(piece) || !p.pName.equals(pName))
            return false;
        return true;
    }
    // TODO checkRep

    private void checkRep() {
        assert pieceState == 0 || pieceState == 1;
        assert pName != null;
        assert piece != null;
    }
    // TODO constructor

    public Piece(int pieceState, String pName, int Px, int Py) {
        super();
        this.pieceState = pieceState;
        this.pName = pName;
        this.piece = new Position(Px, Py);
        checkRep();
    }

}
