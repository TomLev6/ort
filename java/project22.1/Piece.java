public abstract class Piece {

    private boolean killed = false;
    private boolean white = false;

    private boolean safe=true;

    private int pieceValue;

    /*
    pawn = 10
    knight = 30
    bishop = 30
    rook = 50
    queen = 90
    king = 900
     */

    public boolean isSafe() {
        return this.safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public Piece(boolean white)
    {
        this.setWhite(white);
    }

    public boolean isWhite()
    {
        return this.white;
    }

    public void setWhite(boolean white)
    {
        this.white = white;
    }

    public boolean isKilled()
    {
        return this.killed;
    }

    public void setKilled(boolean killed)
    {
        this.killed = killed;
    }

    public int getPieceValue() {
        return this.pieceValue;
    }

    public void setPieceValue(int pieceValue) {
        this.pieceValue = pieceValue;
    }

    public abstract boolean canMove(Board board,
                                    Spot start, Spot end);
}
