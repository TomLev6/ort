public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
        setPieceValue(30);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // we can't move the piece to a spot that has
        // a piece of the same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }

    public void threats(Board board, Spot spot) {
        Spot tmp;

        int[] relativeX = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] relativeY = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int k = 0; k < relativeX.length; k++) {
            int newX = spot.getX() + relativeX[k];
            int newY = spot.getY() + relativeY[k];

            if (isValidPosition(newX, newY)) {
                try {
                    tmp = board.getBox(newX, newY);
                    tmp.getPiece().setSafe(tmp.getPiece().isWhite() == spot.getPiece().isWhite());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
