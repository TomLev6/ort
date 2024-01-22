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
        // knight on the 2-5:2-5
        if (spot.getX() > 1 && spot.getX() < 6 && spot.getY() > 1 && spot.getY() < 6){

            tmp = board.boxes[spot.getX()+2][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-2][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+2][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()-2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()-2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-2][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()+2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()+2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));
        }
        else if (spot.getX() > 1 && spot.getX() < 6 && spot.getY() > 1 && spot.getY() < 6){

            tmp = board.boxes[spot.getX()+2][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-2][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+2][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()-2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()-2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-2][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()+2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()+2];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));
        }
    }
}
