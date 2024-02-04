package main;
import piece.*;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
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
    public static Piece AIpiece=null;

    public static void findMove() {
       /*
       first checking if we can move our king one more step towards the middle squares.
       second if we can't move the king we decide what piece we should move.
       then we decide what square.
        */
        boolean foundMove = false;
        while(!foundMove) {
            // king move
//            for (Piece piece : GamePanel.simPieces) {
//                if (piece.type == Type.KING && piece.color == GamePanel.BLACK) {
//                    targetMove = findMostValuableSquare();
//                    if (piece.canMove(targetMove.getCol(), targetMove.getRow())&&targetMove.moveValue>6) {
//
//                        piece.col = targetMove.getCol();
//                        piece.row = targetMove.getRow();
//                        GamePanel.activeP = piece;
//                        if (GamePanel.activeP.canMove(GamePanel.activeP.col,GamePanel.activeP.row)) {
//                            System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +piece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
//                            movesMadeSoFar.add(targetMove);
//                            foundMove = true;
//                        }
//
//                    }
//                    if (!foundMove&&piece.canMove(targetMove.getCol(), targetMove.getRow())&&targetMove.moveValue>6){
//
//                        piece.col = targetMove.getCol();
//                        piece.row = targetMove.getRow();
//                        GamePanel.activeP = piece;
//                        if (GamePanel.activeP.canMove(GamePanel.activeP.col,GamePanel.activeP.row)) {
//                            System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +piece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
//                            movesMadeSoFar.add(targetMove);
//                            foundMove = true;
//                        }
//
//                    }
//                    if (!foundMove&&piece.canMove(targetMove.getCol(), targetMove.getRow())&&targetMove.moveValue>5){
//
//                        piece.col = targetMove.getCol();
//                        piece.row = targetMove.getRow();
//                        GamePanel.activeP = piece;
//                        if (GamePanel.activeP.canMove(GamePanel.activeP.col,GamePanel.activeP.row)) {
//                            System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +piece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
//                            movesMadeSoFar.add(targetMove);
//                            foundMove = true;
//                        }
//
//                    }
//                }
//            }
            // other piece move

            // first move
//            System.out.println(GamePanel.totalMoves);
            if (GamePanel.totalMoves==2){
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.type == Type.PAWN && piece.color == GamePanel.BLACK) {
                        if (piece.canMove(3,3)){
                            targetMove.setCol(3);
                            targetMove.setRow(3);

                            System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +piece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
                            GamePanel.activeP = piece;
                            GamePanel.activeP.col = targetMove.getCol();
                            GamePanel.activeP.row = targetMove.getRow();
                            movesMadeSoFar.add(targetMove);
                            foundMove = true;

                        }
                        if(!foundMove&&piece.canMove(4,3)){
                            targetMove.setCol(4);
                            targetMove.setRow(3);

                            System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +piece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
                            GamePanel.activeP = piece;
                            GamePanel.activeP.col = targetMove.getCol();
                            GamePanel.activeP.row = targetMove.getRow();
                            movesMadeSoFar.add(targetMove);
                            foundMove = true;

                        }
                    }
                }
            }
            // second or more move

            // let's see if we can capture
            else {

//                System.out.println("looking for move..");
                targetMove = findMostValuableSquare();
                // System.out.println("checking move ..\n" +
                //        " move value:    " + targetMove.moveValue +" ," +AIpiece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
//                AIpiece.col = targetMove.getCol();
//                AIpiece.row = targetMove.getRow();
                GamePanel.activeP = AIpiece;
                AIpiece.col = targetMove.getCol();
                AIpiece.row = targetMove.getRow();

                System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +AIpiece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
                movesMadeSoFar.add(targetMove);
                foundMove = true;
            }
        }
    }

//    public static void findNextMove(){
//        // TODO: need to add when piece can capture, and when the king is in check + change the target to checkmate
//        boolean foundMove = false;
//        while(!foundMove){
//            if (GamePanel.totalMoves<30)
//            {
//                if (GamePanel.totalMoves>4){
//                    // let's check if there is a possible capture
//                    for (Piece pieceB: GamePanel.simPieces){
//                        if (pieceB.color==GamePanel.BLACK){
//                            for (Piece pieceW: GamePanel.simPieces){
//                                if (pieceW.color==GamePanel.WHITE){
//                                    if (pieceB.canMove(pieceW.col,pieceW.row)){
//                                        // there is a white piece black can capture
//                                        targetMove.setCol(pieceW.col);
//                                        targetMove.setRow(pieceW.row);
//                                        if (!movesMadeSoFar.contains(targetMove)){
//                                            GamePanel.activeP = pieceB;
//                                            movesMadeSoFar.add(targetMove);
//                                            foundMove = true;
//                                        }
//                                    }
//                                }
//                            }
//                            // no legal move -> not possible!
//                        }
//                    }
//                }
//                if (GamePanel.totalMoves>8){
//                    //we can bring out the bishop and the queen too
//                    for (Piece piece: GamePanel.simPieces){
//                        if (piece.type==Type.BISHOP&&piece.color==GamePanel.BLACK&&!piece.moved){
//                            targetMove = findMostValuableSquare(piece);
//                            if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
//                                piece.col = targetMove.getCol();
//                                piece.row = targetMove.getRow();
//                                if (!movesMadeSoFar.contains(targetMove)){
//                                    GamePanel.activeP = piece;
//                                    movesMadeSoFar.add(targetMove);
//                                    foundMove = true;
//
//                                }
//                            }
//                            // no legal move -> not possible!
//                        }
//                    }
//                    for (Piece piece: GamePanel.simPieces){
//                        if (piece.type==Type.QUEEN&&piece.color==GamePanel.BLACK&&!piece.moved){
//                            targetMove = findMostValuableSquare(piece);
//                            if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
//                                piece.col = targetMove.getCol();
//                                piece.row = targetMove.getRow();
//                                if (!movesMadeSoFar.contains(targetMove)){
//                                    GamePanel.activeP = piece;
//                                    movesMadeSoFar.add(targetMove);
//                                    foundMove = true;
//
//                                }
//                            }
//                            // no legal move -> not possible!
//                        }
//                    }
//                }
//                else {
//                    // only the opening moves
//                    Random rand = new Random();
//                    int pieceRandom = rand.nextInt(0,10);
//                    if (pieceRandom%2==0){
//                        // knight
//                        for (Piece piece: GamePanel.simPieces){
//                            if (piece.type==Type.KNIGHT&&piece.color==GamePanel.BLACK&&!piece.moved){
//                                targetMove = findMostValuableSquare(piece);
//                                if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
//                                    piece.col = targetMove.getCol();
//                                    piece.row = targetMove.getRow();
//                                    if (!movesMadeSoFar.contains(targetMove)){
//                                        GamePanel.activeP = piece;
//                                        movesMadeSoFar.add(targetMove);
//                                        foundMove = true;
//
//                                    }
//                                }
//                                // no legal move -> not possible!
//                            }
//                        }
//                    }
//                    else {
//                        // pawn move
//                        for (Piece piece: GamePanel.simPieces){
//                            if (piece.type==Type.PAWN&&piece.color==GamePanel.BLACK&&piece.col>2&&piece.col<5){
//                                targetMove = findMostValuableSquare(piece);
//                                if (targetMove.moveValue>2){
//                                    piece.col = targetMove.getCol();
//                                    piece.row = targetMove.getRow();
//                                    if (!movesMadeSoFar.contains(targetMove)){
//                                        GamePanel.activeP = piece;
//                                        movesMadeSoFar.add(targetMove);
//                                        foundMove = true;
//
//                                    }
//                                }
//                                // no legal move -> not possible!
//                            }
//                            else if (piece.type==Type.PAWN&&piece.color==GamePanel.BLACK&&piece.col>1&&piece.col<6){
//                                targetMove = findMostValuableSquare(piece);
//                                if (targetMove.moveValue>0){
//                                    piece.col = targetMove.getCol();
//                                    piece.row = targetMove.getRow();
//                                    if (!movesMadeSoFar.contains(targetMove)){
//                                        GamePanel.activeP = piece;
//                                        movesMadeSoFar.add(targetMove);
//                                        foundMove = true;
//
//                                    }
//                                }
//                                // no legal move -> not possible!
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

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

    public static ArrayList<Move> showLegalMoves(Piece  blackPiece){
        // gets a piece and return a list of al the legal moves
        ArrayList<Move> moves = new ArrayList<>();
        for (Piece piece: GamePanel.simPieces){
            if (piece.equals( blackPiece)){
                for (int col=0;col<8;col++){
                    for (int row=0;row<8;row++){
                        if (blackPiece.canMove(col,row)&&( blackPiece.col!=col|| blackPiece.row!=row)){
                            targetMove.setCol(col);
                            targetMove.setRow(row);
                            if (!moves.contains(targetMove)){
                                moves.add(targetMove);
                            }
                        }
                    }
                }
            }
        }
        for (Move move:moves){
            System.out.println( blackPiece.type + "-> " + move.getCol() + " : " + move.getRow());
        }
        return moves;
    }

    public static Move findMostValuableSquare() {
        Move defaultMove = new Move(0,0);
        ArrayList<Move> possibleMoves;
        int maxMoveValue = 0;
        for (Piece blackPiece : GamePanel.simPieces) {
            if (blackPiece.color == GamePanel.BLACK && blackPiece.type != null) {
                possibleMoves = showLegalMoves(blackPiece);
                for (Move move : possibleMoves) {
                    int currentMoveValue = 0; // Initialize currentMoveValue for each move
                    // Check piece type and update currentMoveValue accordingly
                    switch (blackPiece.type) {
                        case ROOK:
                            if ((move.getCol() > 0 && move.getCol() < 7 && (move.getRow() == 1 || move.getRow() == 6))) {
                                currentMoveValue = 1;
                            }
                            break;
                        case KNIGHT:
                            if (move.getCol() > 1 && move.getCol() < 6 && move.getRow() > 1 && move.getRow() < 4) {
                                currentMoveValue = 5;
                            }
                            break;
                        case PAWN:
                            if (move.getCol() > 2 && move.getCol() < 5 && move.getRow() > 2 && move.getRow() < 5) {
                                currentMoveValue = 5;
                            }
                            break;
                        case BISHOP:
                            if (move.getCol() > 1 && move.getCol() < 6 && move.getRow() > 1 && move.getRow() < 4) {
                                currentMoveValue = 3;
                            }
                            break;
                        case KING:
//                            System.out.println("KING    " + move.getCol() +" :"  + blackPiece.col + "   |  " + move.getRow() +" :"  + blackPiece.row );
                            if (move.getCol() == 4 && move.getRow() > 1 && move.getRow() < 5) {
                                currentMoveValue = 9;
                            }
                            else if (move.getCol() == 3 && move.getRow() > 0) {
                                currentMoveValue = 8;
                                }
                                else if (move.getCol() > 2 && move.getCol() < 6 && move.getRow() > 0) {
                                    currentMoveValue = 7;

                                }
                            break;
                    }
                    // Update maxMoveValue and defaultMove if currentMoveValue is greater
                    if (currentMoveValue > maxMoveValue) {
                        maxMoveValue = currentMoveValue;
                        defaultMove.setMoveValue(maxMoveValue);
                        System.out.println("current value:" + currentMoveValue + "  max value:  " + maxMoveValue);
                        move.setMoveValue(maxMoveValue);
                        AIpiece = blackPiece;
                        defaultMove = move;
                        System.out.println(defaultMove);
                    }
                    if (currentMoveValue>8){
                        // king move
                        return move;
                    }
                    if (currentMoveValue>7){
                        // king move
                        return move;
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