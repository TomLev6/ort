package piece;

import main.GamePanel;
import main.Type;

public class Queen extends Piece{
    public Queen(int color, int col, int row) {
        super(color, col, row);
        type = Type.QUEEN;
        if (color== GamePanel.WHITE){
            image = getImage("queen");
        }
        else  image = getImage("queen1");
    }
    public boolean canMove(int targetCol, int targetRow){
        if (isWithinBoard(targetCol,targetRow)&&(!isSameSquare(targetCol,targetRow))){
            // the formula to check if the queen move is valid
            if( targetCol==preCol || targetRow==preRow)
            {
                if(isValidSquare(targetCol, targetRow)&&!pieceIsOnStraightLine(targetCol,targetRow)){
                    return true;
                }
            }
            if( Math.abs(targetCol-preCol)==Math.abs(targetRow-preRow))
            {
                if(isValidSquare(targetCol, targetRow)&&!pieceIsOnDiagonalLine(targetCol,targetRow)){
                    return true;
                }
            }
        }
        return false;

    }
}
