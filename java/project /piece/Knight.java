package piece;

import main.GamePanel;
import main.Type;

public class Knight extends Piece{
    public Knight(int color, int col, int row) {
        super(color, col, row);
        type = Type.KNIGHT;
        if (color== GamePanel.WHITE){
            image = getImage("knight");
        }
        else  image = getImage("knight1");
    }
    public boolean canMove(int targetCol, int targetRow){
        if (isWithinBoard(targetCol,targetRow)){
            // the formula to check if the knight move is valid
            if( Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {// up / down / left / right
                return isValidSquare(targetCol, targetRow);

            }
        }
        return false;

    }
}
