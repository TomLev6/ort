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
        if (spot.getX() > 0 && spot.getX() < 7 && spot.getY() > 0 && spot.getY() < 7)
            //king not on the edges 1-6: 1-6
        {
            tmp = board.boxes[spot.getX()+1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

        }
        //king on the upper row without corners 1-6:0
        else if(spot.getX() > 0 && spot.getX() < 7 && spot.getY() == 0){


            tmp = board.boxes[spot.getX()+1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));
        }
        //king on the upper row without corners 1-6:7
        else if(spot.getX() > 0 && spot.getX() < 7 && spot.getY() == 7){

            tmp = board.boxes[spot.getX()+1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

        }
        //king on the left column without corners 0:1-6
        else if(spot.getX() == 0 && spot.getY() > 0 && spot.getY() < 7){

            tmp = board.boxes[spot.getX()+1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));
        }
        //king on the left column without corners 7:1-6
        else if(spot.getX() == 7 && spot.getY() > 0 && spot.getY() < 7) {

            tmp = board.boxes[spot.getX()][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

        }
        //corners - king on the right upper corner 7:0
        else if (spot.getX() == 7 && spot.getY() == 0) {

            tmp = board.boxes[spot.getX()][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));


        }
        //corners - king on the right lower corner 7:7
        else if (spot.getX() == 7 && spot.getY() == 7) {

            tmp = board.boxes[spot.getX()][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()-1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

        }
        //corners - king on the left upper corner 0:0
        else if (spot.getX() == 0 && spot.getY() == 0) {

            tmp = board.boxes[spot.getX()][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()+1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

        }
        //corners - king on the left lower corner 0:7
        else if (spot.getX() == 0 && spot.getY() == 7) {

            tmp = board.boxes[spot.getX()][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()-1];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

            tmp = board.boxes[spot.getX()+1][spot.getY()];
            tmp.getPiece().setSafe((tmp.getPiece().isWhite()) == (spot.getPiece().isWhite()));

        }
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
