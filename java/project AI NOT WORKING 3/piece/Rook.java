package piece;

import main.GamePanel;

public class Rook extends Piece{
    public Rook(int color, int col, int row) {
        super(color, col, row);
        type = Type.ROOK;
        if (color== GamePanel.WHITE){
            image = getImage("rook");
        }
        else  image = getImage("rook1");
    }
    public boolean canMove(int targetCol, int targetRow){
        if (isWithinBoard(targetCol,targetRow)&&(!isSameSquare(targetCol,targetRow))){
            // the formula to check if the rook move is valid
            if( targetCol==preCol || targetRow==preRow)
            {
                if(isValidSquare(targetCol, targetRow)&&!pieceIsOnStraightLine(targetCol,targetRow)){
                    return true;
                }
            }
        }
        return false;

    }
}
