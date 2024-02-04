package piece;

import main.GamePanel;
import main.Type;

public class Pawn extends Piece{

    public Pawn(int color, int col, int row) {
        super(color, col, row);
        type = Type.PAWN;
        if (color== GamePanel.WHITE){
            image = getImage("pawn");
        }
        else  image = getImage("pawn1");
    }
    public boolean canMove(int targetCol, int targetRow){
        if (isWithinBoard(targetCol,targetRow)){
            // the formula to check if the pawn move is valid
            // define the move value based on its color
            int moveValue;
            if (color == GamePanel.WHITE) {// if the piece is white ,so it can only move up
                moveValue = -1;
            }
            else// if the piece is black it can only move down
            {
                moveValue = 1;
            }
            // check the hitting piece
            hittingP = getHittingP(targetCol,targetRow);

            // 1 square movement
            if(targetCol==preCol&&targetRow==preRow+moveValue&&hittingP==null){
                return true;
            }
            // 2 squares movement
            if (targetCol==preCol&&targetRow==preRow+moveValue * 2 &&hittingP==null&&!moved&&!pieceIsOnStraightLine(targetCol,targetRow)){
                return true;
            }
            // diagonal movement & capture (if a piece is on a square diagonally in front of the pawn)
            if (Math.abs(targetCol-preCol)==1 && targetRow==preRow+moveValue && hittingP!=null && hittingP.color!=color){
                return true;
            }
            // en passant
            if (Math.abs(targetCol-preCol)==1 && targetRow==preRow+moveValue ){
                // checking what piece we have land on
                for (Piece piece: GamePanel.simPieces){
                    if (piece.col == targetCol&&piece.row==preRow&&piece.twoStepped){
                        hittingP = piece;
                        return true;
                    }
                }
            }
            // promote


        }
        return false;

    }
}
