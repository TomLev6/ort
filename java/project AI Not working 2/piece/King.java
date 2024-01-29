package piece;

import main.GamePanel;
import main.Mouse;
import main.Type;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        type = Type.KING;
        if (color== GamePanel.WHITE){
            image = getImage("king");
        }
        else  image = getImage("king1");
    }

    public boolean canMove(int targetCol, int targetRow){
        if (isWithinBoard(targetCol,targetRow)){
            // the formula to check if the king move is valid
            // movement
            if( Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 // up / down / left / right
                    || Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1){
                if (isValidSquare(targetCol,targetRow)){
                    return true;
                }

            }
            // castling
            if (!moved){
                // right castling
                if (targetCol == preCol+2 & targetRow==preRow&&!pieceIsOnStraightLine(targetCol,targetRow)){
                    for (Piece piece: GamePanel.simPieces){
                        if (piece.col== preCol+3 && piece.row==preRow&&!piece.moved){
                            GamePanel.castlingP = piece;
                            return true;
                        }
                    }
                }
                // left castling
                if (targetCol == preCol-2 & targetRow==preRow &&!pieceIsOnStraightLine(targetCol,targetRow)){
                    Piece pieces[] = new Piece[2];
                    for (Piece piece: GamePanel.simPieces){
                        if (piece.col== preCol-3 && piece.row==targetRow){
                            pieces[0] = piece;
                        }
                        if (piece.col== preCol-4 && piece.row==targetRow){
                            pieces[1] = piece;
                        }
                        if (pieces[0]==null&& pieces[1]!= null&&!pieces[1].moved){
                            GamePanel.castlingP = pieces[1];
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }
}
