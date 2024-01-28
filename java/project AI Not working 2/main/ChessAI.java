package main;
import piece.*;

import java.util.ArrayList;
import java.util.Random;

public class ChessAI {
    // Constants for piece values
    private static final int PAWN_VALUE = 1;
    private static final int KNIGHT_VALUE = 3;
    private static final int BISHOP_VALUE = 3;
    private static final int ROOK_VALUE = 5;
    private static final int QUEEN_VALUE = 9;
    private static final int KING_VALUE = 90;
    public static Move targetMove = new Move(0,0);
    public static ArrayList<Move> movesMadeSoFar = new ArrayList<>();

    public static void findNextMove(){
        // TODO: need to add when piece can capture, and when the king is in check + change the target to checkmate
        boolean foundMove = false;
        while(!foundMove){
            if (GamePanel.totalMoves<30)
            {
                if (GamePanel.totalMoves>4){
                    // let's check if there is a possible capture
                    for (Piece pieceB: GamePanel.simPieces){
                        if (pieceB.color==GamePanel.BLACK){
                            for (Piece pieceW: GamePanel.simPieces){
                                if (pieceW.color==GamePanel.WHITE){
                                    if (pieceB.canMove(pieceW.col,pieceW.row)){
                                        // there is a white piece black can capture
                                        targetMove.setCol(pieceW.col);
                                        targetMove.setRow(pieceW.row);
                                        if (!movesMadeSoFar.contains(targetMove)){
                                            GamePanel.activeP = pieceB;
                                            movesMadeSoFar.add(targetMove);
                                            foundMove = true;
                                        }
                                    }
                                }
                            }
                            // no legal move -> not possible!
                        }
                    }
                }
                if (GamePanel.totalMoves>8){
                    //we can bring out the bishop and the queen too
                    for (Piece piece: GamePanel.simPieces){
                        if (piece.type==Type.BISHOP&&piece.color==GamePanel.BLACK&&!piece.moved){
                            targetMove = findMostValuableSquare(piece);
                            if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                piece.col = targetMove.getCol();
                                piece.row = targetMove.getRow();
                                if (!movesMadeSoFar.contains(targetMove)){
                                    GamePanel.activeP = piece;
                                    movesMadeSoFar.add(targetMove);
                                    foundMove = true;

                                }
                            }
                            // no legal move -> not possible!
                        }
                    }
                    for (Piece piece: GamePanel.simPieces){
                        if (piece.type==Type.QUEEN&&piece.color==GamePanel.BLACK&&!piece.moved){
                            targetMove = findMostValuableSquare(piece);
                            if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                piece.col = targetMove.getCol();
                                piece.row = targetMove.getRow();
                                if (!movesMadeSoFar.contains(targetMove)){
                                    GamePanel.activeP = piece;
                                    movesMadeSoFar.add(targetMove);
                                    foundMove = true;

                                }
                            }
                            // no legal move -> not possible!
                        }
                    }
                }
                else {
                    // only the opening moves
                    Random rand = new Random();
                    int pieceRandom = rand.nextInt(0,10);
                    if (pieceRandom%2==0){
                        // knight
                        for (Piece piece: GamePanel.simPieces){
                            if (piece.type==Type.KNIGHT&&piece.color==GamePanel.BLACK&&!piece.moved){
                                targetMove = findMostValuableSquare(piece);
                                if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                    piece.col = targetMove.getCol();
                                    piece.row = targetMove.getRow();
                                    if (!movesMadeSoFar.contains(targetMove)){
                                        GamePanel.activeP = piece;
                                        movesMadeSoFar.add(targetMove);
                                        foundMove = true;

                                    }
                                }
                                // no legal move -> not possible!
                            }
                        }
                    }
                    else {
                        // pawn move
                        for (Piece piece: GamePanel.simPieces){
                            if (piece.type==Type.PAWN&&piece.color==GamePanel.BLACK&&piece.col>2&&piece.col<5){
                                targetMove = findMostValuableSquare(piece);
                                if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                    piece.col = targetMove.getCol();
                                    piece.row = targetMove.getRow();
                                    if (!movesMadeSoFar.contains(targetMove)){
                                        GamePanel.activeP = piece;
                                        movesMadeSoFar.add(targetMove);
                                        foundMove = true;

                                    }
                                }
                                // no legal move -> not possible!
                            }
                            else if (piece.type==Type.PAWN&&piece.color==GamePanel.BLACK&&piece.col>1&&piece.col<6){
                                targetMove = findMostValuableSquare(piece);
                                if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                    piece.col = targetMove.getCol();
                                    piece.row = targetMove.getRow();
                                    if (!movesMadeSoFar.contains(targetMove)){
                                        GamePanel.activeP = piece;
                                        movesMadeSoFar.add(targetMove);
                                        foundMove = true;

                                    }
                                }
                                // no legal move -> not possible!
                            }
                        }
                    }
                }
            }
        }
    }

    public static Piece whichPieceMoved() {
        // detects which piece has been moved
        Piece MovedPiece = null;
        int maxPieceValue = 0;
        if (GamePanel.totalMoves == 1) {
            for (Piece piece: GamePanel.simPieces){
                if (piece.type!=null&&piece.color==GamePanel.WHITE){
                    switch (piece.type){
                        case PAWN:
                            if (piece.moved){
                                // it means that this specific pawn moved
                                MovedPiece = piece;
                                return MovedPiece;
                            }
                        case KNIGHT:
                            if (piece.moved){
                                MovedPiece = piece;
                                return MovedPiece;
                            }
                    }
                }
            }

        }
        else {
            for (Piece pieceW: GamePanel.simPieces) {
                if (pieceW.type != null && pieceW.color == GamePanel.WHITE) {
                    for (Piece pieceB: GamePanel.simPieces){
                        if (pieceB.type!=null&&pieceB.color==GamePanel.BLACK){
                            if (pieceW.canMove(pieceB.col, pieceB.row)){
                                if (getPieceValue(pieceB)>maxPieceValue){
                                    maxPieceValue = getPieceValue(pieceB);
                                    MovedPiece = pieceW;
                                    GamePanel.threatendAIPiece = pieceB;
                                }
                            }
                        }
                    }

                }
            }
        }
        return MovedPiece;
    }

    public static ArrayList<Move> showLegalMoves(Piece pieceAI){
        // gets a piece and return a list of al the legal moves
        ArrayList<Move> moves = new ArrayList<>();
        for (Piece piece: GamePanel.simPieces){
            if (piece==pieceAI){
                for (int col=0;col<8;col++){
                    for (int row=0;row<8;row++){
                        if (piece.canMove(col,row)){
                            targetMove.setCol(col);
                            targetMove.setRow(row);

                        }
                    }
                }
            }
        }
        return moves;
    }

    public static Move findMostValuableSquare(Piece pieceAI){
        Move defaultMove = new Move(0,0);
        ArrayList<Move> possibleMoves;
        if (pieceAI.type!=null){
            switch (pieceAI.type){
                case ROOK:
                    possibleMoves = showLegalMoves(pieceAI);
                    for (Move move: possibleMoves){
                        if (move.getCol()>0&&move.getCol()<7&&move.getRow()==1||move.getRow()==6){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                        else if (move.getCol()==0||move.getCol()==7&&move.getRow()>0||move.getRow()<7){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                    }
                case KNIGHT:
                    possibleMoves = showLegalMoves(pieceAI);
                    for (Move move: possibleMoves){
                        if (move.getCol()>2&&move.getCol()<5&&move.getRow()>2&&move.getRow()<5){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                        else if (move.getCol()>1&&move.getCol()<6&&move.getRow()>1&&move.getRow()<6){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                    }
                case PAWN:
                    possibleMoves = showLegalMoves(pieceAI);
                    for (Move move: possibleMoves){
                        if (move.getCol()>2&&move.getCol()<5&&move.getRow()>2&&move.getRow()<5){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                        else if (move.getCol() > 1 && move.getCol() < 6 && move.getRow() == 2){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                    }
                case BISHOP:
                    possibleMoves = showLegalMoves(pieceAI);
                    for (Move move: possibleMoves)
                    {
                        if (move.getCol()>1&&move.getCol()<6&&move.getRow()>1&&move.getRow()<4){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                        else if (move.getCol() > 1 && move.getCol() < 6 && move.getRow() == 2) {
                            for (Piece piece : GamePanel.simPieces) {
                                if (piece.color != pieceAI.color) {
                                    if (!piece.canMove(move.getCol(), move.getRow())) {
                                        return move;
                                    }
                                }
                            }
                        }
                    }
                
            }
        }
        return defaultMove;
    }
    public void search(int depth) {
        // evaluate the position after every move
        int score = evaluateMaterial();


        ArrayList<Move> moves;




    }

    private int evaluateMaterial()
    {
        int whiteMaterial = 0;
        int blackMaterial = 0;

        for (Piece piece : GamePanel.simPieces) {
            if (piece.color == GamePanel.WHITE) {
                // piece is white
                whiteMaterial += getPieceValue(piece);
            } else {
                // piece is black
                blackMaterial += getPieceValue(piece);
            }
        }

        return whiteMaterial - blackMaterial;
    }

    private static int getPieceValue(Piece piece) {
        if (piece != null && piece.type != null) {
            switch (piece.type) {
                case PAWN:
                    return PAWN_VALUE;
                case KNIGHT:
                    return KNIGHT_VALUE;
                case BISHOP:
                    return BISHOP_VALUE;
                case ROOK:
                    return ROOK_VALUE;
                case QUEEN:
                    return QUEEN_VALUE;
                default:
                    return 0;  // Unknown piece type
            }
        }
        return 0;
    }
}