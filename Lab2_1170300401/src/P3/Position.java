package P3;

public class Position {

    private int x;
    private int y;

    // Abstraction function:
    // TODO x and y represent a piece's position
    // Representation invariant:
    // TODO x >=0 y>=0 if chess x(y)<8 if go x(y)<19
    // Safety from rep exposure:
    // TODO All fields are private

    @Override public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (!(obj instanceof Position))
            return false;
        Position pos = (Position) obj;
        if (x != pos.x || y != pos.y)
            return false;
        return true;
    }

    public int getX() {
        checkRep();
        return x;
    }

    public int getY() {
        checkRep();
        return y;
    }

    // TODO constructor
    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        checkRep();
    }
    // TODO checkRep

    private void checkRep() {
        assert x >= 0 && y >= 0;
    }

}
