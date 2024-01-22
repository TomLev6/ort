public class King extends Piece {
    private boolean castlingDone = false;

    public King(boolean white)
    {
        super(white);
        setPieceValue(900);
    }

    public boolean isCastlingDone()
    {
        return this.castlingDone;
    }

    public void setCastlingDone(boolean castlingDone)
    {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end)
    {
        // we can't move the piece to a Spot that
        // has a piece of the same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        if (!end.getPiece().isSafe()) return false;

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if (x + y == 1) {
            // check if this move will not result in the king
            // being attacked if so return true
            return true;
        }

        return this.isValidCastling(board, start, end); // ?
    }

    private boolean isValidCastling(Board board,
                                    Spot start, Spot end)
    {

        if (this.isCastlingDone()) {
            return false;
        }
        if (!start.getPiece().isSafe()||!end.getPiece().isSafe()) return false;
        return true;


    }
    public void threats(Board board, Spot spot) {
        Spot tmp;

        int[] relativeX = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] relativeY = {1, 0, -1, 1, -1, 1, 0, -1};

        for (int k = 0; k < relativeX.length; k++) {
            int newX = spot.getX() + relativeX[k];
            int newY = spot.getY() + relativeY[k];

            if (isValidPosition(newX, newY)) {
                tmp = board.boxes[newX][newY];
                tmp.getPiece().setSafe(tmp.getPiece().isWhite() == spot.getPiece().isWhite());
            }
        }
    }
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public void isCastlingMove(Spot start, Spot end,Board board)
    {
        if (canMove(board,start,end)){
            setCastlingDone(true);
        }
        // check if the starting and
        // ending position are correct
    }
}
