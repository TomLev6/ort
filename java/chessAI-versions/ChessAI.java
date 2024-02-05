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
    public static Move lastMove= new Move(0,0);;
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
                Move AImove = findMostValuableSquare();
                if (lastMove.equals(AImove)){
                    while (lastMove.equals(AImove)){
                        AImove = findMostValuableSquare();
                    }
                }
                lastMove = AImove;
                GamePanel.activeP = AIpiece;
                AIpiece.col = AImove.getCol();
                AIpiece.row = AImove.getRow();
                System.out.println("found move ! move value:    " + AImove.moveValue +" ," +AIpiece.type + " -> " + AImove.getCol() + " : " + AImove.getRow());
                movesMadeSoFar.add(AImove);
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

    public static ArrayList<Move> showLegalMoves(Piece blackPiece){
        //TODO: GETTING ALL OF THE POSSIBLE MOVES, STILL RECEIVING A LACK OF MOVES
        // gets a piece and return a list of all the legal moves
        ArrayList<Move> moves = new ArrayList<>();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if (blackPiece.canMove(col, row)) {
                    Move targetMove = new Move(col, row);
                    if (!moves.contains(targetMove)) {
                        moves.add(targetMove);
                    }
                }
            }
        }
//        for (Move move:moves){
//            System.out.println( blackPiece.type + "-> " + move.getCol() + " : " + move.getRow());
//        }
        return moves;
    }

    public static Move findMostValuableSquare() {
        // TODO: if the king is in check

        Move defaultMove = new Move(0,0);
        int maxMoveValue = 1;
        for (Piece blackPiece : GamePanel.simPieces) {
            if (blackPiece.color == GamePanel.BLACK && blackPiece.type != null) {
                ArrayList<Move> possibleMoves = showLegalMoves(blackPiece);
                for (Move move : possibleMoves) {
                    int currentMoveValue = 0; // Initialize currentMoveValue for each move
                    // Check piece type and update currentMoveValue accordingly
                    switch (blackPiece.type) {
                        case ROOK:
                            // possible capture
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.color==GamePanel.WHITE){
                                    // we might, can capture
                                    if (getPieceValue(blackPiece)<getPieceValue(whitePiece)){
                                        if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                            currentMoveValue = 5;
                                            break;
                                        }
                                    }
                                }
                            }
                            // develop
                            if ((move.getCol() > 0 && move.getCol() < 6 && (move.getRow() >= 0 || move.getRow() == 6))) {
                                currentMoveValue = 3;
                            }
                            break;
                        case KNIGHT:
                            if (move.getCol() > 1 && move.getCol() < 6 && move.getRow() == 2) {
                                currentMoveValue = 5;
                                break;
                            }
                            // possible capture
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.color==GamePanel.WHITE){
                                    // we might, can capture
                                    if (getPieceValue(blackPiece)+3<getPieceValue(whitePiece)){
                                        if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                            currentMoveValue = 7;

                                        }
                                    }
                                }
                            }
                            break;
                        case PAWN:
                            if (move.getCol() > 2 && move.getCol() < 5 && move.getRow() > 2&& move.getRow() <5) {
                                currentMoveValue = 5;
                            }
                            // possible capture
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.color==GamePanel.WHITE){
                                    // we might, can capture
                                    if (getPieceValue(blackPiece)+5<getPieceValue(whitePiece)){
                                        if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                            currentMoveValue = 7;

                                        }
                                    }
                                    else if (getPieceValue(blackPiece)+3<getPieceValue(whitePiece)){
                                        if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                            currentMoveValue = 6;

                                        }
                                    }
                                        else {
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                                currentMoveValue = 5;

                                            }
                                        }
                                }
                            }
                            break;
                        case BISHOP:
                            if (move.getCol() > 1 && move.getCol() < 6 && move.getRow() > 1 && move.getRow() < 4&& !blackPiece.moved) {
                                currentMoveValue = 3;
                                break;
                            }
                            // possible capture
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.color==GamePanel.WHITE){
                                    // we might, can capture
                                    if (getPieceValue(blackPiece)+3<getPieceValue(whitePiece)){
                                        if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                            currentMoveValue = 6;

                                        }
                                    }
                                }
                            }
                            break;
                        case KING:
                           if (isSafeSquare(move)){
                               if ((move.getCol() == 3 ||move.getCol() == 4) && (move.getRow() == 3 || move.getRow() ==4)) {
                                   // win move
                                   currentMoveValue = 9;
                                   break;
                               }
                               else if (move.getCol() == blackPiece.col && move.getRow() > blackPiece.row) {
                                   // move towards the middle vertically
                                   currentMoveValue = 6;
                                   break;
                               }
//                                else if (move.getCol() > 2 && move.getCol() < 6 && move.getRow() > 0) {
//                                    //
//                                    currentMoveValue = 3;
//
//                                }
                               // possible capture
                               for (Piece whitePiece: GamePanel.simPieces){
                                   if (whitePiece.color==GamePanel.WHITE){
                                       // we might, can capture
                                       if (2<getPieceValue(whitePiece)){
                                           if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                               currentMoveValue = 5;
                                           }
                                       }
                                   }
                               }
                           }
                          break;
                        case QUEEN:
                            if ((move.getCol() > 1 && move.getCol() < 6 && (move.getRow() > 0 || move.getRow() < 6))&&!blackPiece.moved) {
                                currentMoveValue = 2;
                            }
                            // possible capture
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.color==GamePanel.WHITE){
                                    // we might, can capture
                                    if (4<getPieceValue(whitePiece)){
                                        if (blackPiece.canMove(whitePiece.col, whitePiece.row)){
                                            currentMoveValue = 4;
                                        }
                                    }
                                }
                            }
                            break;
                    }
//                    System.out.println("\n before checking move: " + defaultMove.toStr() + "\n");
                    // Update maxMoveValue and defaultMove if currentMoveValue is greater
                    if (currentMoveValue > maxMoveValue) {
                        maxMoveValue = currentMoveValue;
                        defaultMove.setMoveValue(maxMoveValue);
                        move.setMoveValue(maxMoveValue);
                        defaultMove.setCol(move.getCol());
                        defaultMove.setRow(move.getRow());
                        AIpiece = blackPiece;

                    }
                }
            }
        }
//        System.out.println("returns.....  max value: " + maxMoveValue+ "  new move: " + defaultMove.toStr() + "  for " + AIpiece.type );
        return defaultMove;
    }

    public static boolean AIkingInCheck(){
        Piece blackKing=null;
        for (Piece blackPiece: GamePanel.simPieces){
            if (blackPiece.color==GamePanel.BLACK){
                if (blackPiece.type==Type.KING){
                    blackKing=blackPiece;
                }
            }
        }
        if (blackKing!=null){
            for (Piece whitePiece: GamePanel.simPieces){
                if (whitePiece.color==GamePanel.WHITE){
                    if (whitePiece.canMove(blackKing.col, blackKing.row)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isSafeSquare(Move move){
        for (Piece whitePiece: GamePanel.simPieces){
            if (whitePiece.color==GamePanel.WHITE){
                if (whitePiece.canMove(move.getCol(), move.getRow())){
                    return false;
                }
            }
        }
        return true;
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
