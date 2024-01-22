public class Knight extends Piece {
    public Knight(boolean white)
    {
        super(white);
        setPieceValue(30);
    }

    @Override
    public boolean canMove(Board board, Spot start,
                           Spot end)
    {
        // we can't move the piece to a spot that has
        // a piece of the same colour
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
    public void threats(Board board, Spot spot) {
        Spot tmp;

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (Math.abs(i * j) == 2 && isValidPosition(spot.getX() + i, spot.getY() + j)) {
                    tmp = board.boxes[spot.getX() + i][spot.getY() + j];
                    tmp.getPiece().setSafe(tmp.getPiece().isWhite() == spot.getPiece().isWhite());
                }
            }
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

}
