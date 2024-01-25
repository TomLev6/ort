package piece;

import main.Board;
import main.GamePanel;
import main.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

public class Piece {
    public Type type;
    public BufferedImage image;
    public int x,y;
    public int col, row, preCol, preRow;
    public int color;
    public Piece hittingP;
    public boolean moved, twoStepped;

    public Piece(int color, int col,int row){
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    public BufferedImage getImage(String imagePath){
        BufferedImage image = null;
        try {
            image = ImageIO.read((getClass().getResourceAsStream(imagePath + ".png")));// without (Objects.requireNonNull)
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col){
        return col * Board.SQUARE_SIZE;
    }
    public int getY(int row){
        return row * Board.SQUARE_SIZE;
    }
    public int getCol(int x){
        // detect the current square by the piece center instead of left upper corner
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    public int getRow(int y){
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    public int getIndex(){
        for (int index =0;index<GamePanel.simPieces.size();index++){
            if (GamePanel.simPieces.get(index)==this){
                return index;
            }
        }
        return 0;
    }
    public void updatePosition(){
        // updates the x, y based on their current col and row,
        // adjust the position on the center of the square
        if (type == Type.PAWN){
            if (Math.abs((row-preRow))==2){
                twoStepped = true;
            }
        }
        x = getX(col);
        y = getY(row);
        preRow = getRow(y);
        preCol = getCol(x);
        moved = true;
    }
    public boolean canMove(int targetCol, int targetRow){
        return false;
    }
    public boolean isSameSquare(int targetCol, int targetRow){
        return targetCol == preCol && targetRow == preRow;
    }
    public boolean isWithinBoard(int targetCol, int targetRow){
        return targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7;
    }
    public Piece getHittingP(int targetCol, int targetRow){
        // scan if there is a piece that has the exact row and col as the moving piece,
        // and if there is return this piece

        for (Piece piece: GamePanel.simPieces) {
            if (piece.col == targetCol && piece.row==targetRow && piece!= this){
                return piece;
            }

        }
        return null;
    }
    public boolean isValidSquare(int targetCol, int targetRow){
        // getting the hitting piece ,if there isn't it means that the square
        // isn't occupied so the player can move
        hittingP = getHittingP( targetCol, targetRow);
        if (hittingP==null){
            return true;
        }
        else {// the square is occupied
            if (hittingP.color!=this.color){
                // if the color is different the piece can be captured
                return true;
            }
            else {
                hittingP = null;
            }
        }
        return false;

    }
    public void draw(Graphics2D g2){
        g2.drawImage(image, x,y,Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
    public boolean pieceIsOnStraightLine(int targetCol, int targetRow){
        // when the piece is moving to the left
        for (int c = preCol-1;c>targetCol;c--){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == c && piece.row==targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        // when the piece is moving to the right
        for (int c = preCol+1;c<targetCol;c++){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == c && piece.row==targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        // when the piece is moving up
        for (int r = preRow-1;r>targetRow;r--){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == targetCol && piece.row==r){
                    hittingP = piece;
                    return true;
                }
            }
        }
        // when the piece is moving down
        for (int r = preRow+1;r<targetRow;r++){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == targetCol && piece.row==r){
                    hittingP = piece;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean pieceIsOnDiagonalLine(int targetCol, int targetRow){
        if (targetRow<preRow){
            // up left
            for (int c = preCol-1;c>targetCol;c--){
                int diff = Math.abs(c-preCol);
                for (Piece piece: GamePanel.simPieces){
                    if (piece.col==c && piece.row==preRow-diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }
            // up right
            for (int c = preCol+1;c<targetCol;c++){
                int diff = Math.abs(c-preCol);
                for (Piece piece: GamePanel.simPieces){
                    if (piece.col==c && piece.row==preRow-diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }

        if (targetRow>preRow){
            // down left
            for (int c = preCol-1;c>targetCol;c--)
            {
                int diff = Math.abs(c-preCol);
                for (Piece piece: GamePanel.simPieces){
                    if (piece.col==c && piece.row==preRow+diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }
            // down right
            for (int c = preCol+1;c<targetCol;c++){
                int diff = Math.abs(c-preCol);
                for (Piece piece: GamePanel.simPieces){
                    if (piece.col==c && piece.row==preRow+diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void resetPosition() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

}
