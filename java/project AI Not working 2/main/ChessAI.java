package main;
import piece.*;

import java.util.ArrayList;
import java.util.Map;
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
//    private ArrayList<Move> movesMadeSoFar;

    public static void findNextMove(){
        // TODO: need to add when piece can capture, and when the king is in check + change the target to checkmate
        ArrayList<Move> movesMadeSoFar = new ArrayList<>();
        boolean foundMove = false;
        while(!foundMove){
            System.out.println("FoundMove : " + foundMove);

            // openings moves
            if (GamePanel.totalMoves<50)
            {
                System.out.println("less than 20 moves made");
//                if (GamePanel.totalMoves>30){
//                    System.out.println("checking for possible capture..");
//
//                    // let's check if there is a possible capture
//                    for (Piece pieceB: GamePanel.simPieces){
//                        if (pieceB.color==GamePanel.BLACK){
//                            for (Piece pieceW: GamePanel.simPieces){
//                                if (pieceW.color==GamePanel.WHITE){
//                                    if (pieceB.canMove(pieceW.col,pieceW.row)){
//                                        // there is a white piece black can capture
//                                        System.out.println("found possible capture in:  " + pieceW.col + ": " + pieceW.row + " by " + pieceB.type);
//
//                                        targetMove.setCol(pieceW.col);
//                                        targetMove.setRow(pieceW.row);
////                                        if (GamePanel.pieceAndMoves.containsKey(pieceB) &&GamePanel.pieceAndMoves.get(pieceB).getCol()!=pieceB.col||
////                                                GamePanel.pieceAndMoves.get(pieceB).getRow()!=pieceB.row){
////                                            GamePanel.activeP = pieceB;
////                                            GamePanel.pieceAndMoves.put(pieceB,targetMove);
////                                            foundMove = true;
////
//                                        if (!movesMadeSoFar.contains(targetMove))
//                                        {
//                                            GamePanel.activeP = pieceB;
////                                        GamePanel.activeP.hittingP = pieceW;
//                                            movesMadeSoFar.add(targetMove);
//                                            foundMove = true;
//                                        }
//
//                                    }
//                                }
//                            }
//                            // no legal move -> not possible!
//                        }
//                    }
//                }

                // TODO: need to add when the king is in check!
                // TODO: smarter moves, avoiding checkmate \ blocking checks
                
                if (!foundMove&&GamePanel.totalMoves > 9){
                    System.out.println("checking for possible queen or bishop move..");

                    for (Piece piece: GamePanel.simPieces){
                        if (piece.type==Type.QUEEN&&piece.color==GamePanel.BLACK){//&&!piece.moved
                            targetMove = findMostValuableSquare(piece);
                            System.out.println(piece.type + "-> targetMove: " + targetMove.getCol()+":"+targetMove.getRow());
                            if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                piece.col = targetMove.getCol();
                                piece.row = targetMove.getRow();
//
                                System.out.println("Queen " + piece.moved);

                                GamePanel.activeP = piece;
                                movesMadeSoFar.add(targetMove);
                                foundMove = true;


                            }
                            // no legal move -> not possible!
                        }
                        else if (piece.type==Type.BISHOP&&piece.color==GamePanel.BLACK){//&&!piece.moved
                            targetMove = findMostValuableSquare(piece);
                            System.out.println(piece.type + "-> targetMove: " + targetMove.getCol()+":"+targetMove.getRow());
                            if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                piece.col = targetMove.getCol();
                                piece.row = targetMove.getRow();
//
                                System.out.println("Queen " + piece.moved);

                                GamePanel.activeP = piece;
                                movesMadeSoFar.add(targetMove);
                                foundMove = true;


                            }
                            // no legal move -> not possible!
                        }
                    }
                }
                if (!foundMove){

                    // only the opening moves
                    Random rand = new Random();
                    int pieceRandom = rand.nextInt(0,10);
                    if (pieceRandom%2==0){
                        System.out.println("checking for possible knight move..");

                        // knight
                        for (Piece piece: GamePanel.simPieces){
                            if (piece.type==Type.KNIGHT&&piece.color==GamePanel.BLACK){ // &&!piece.moved
                                targetMove = findMostValuableSquare(piece);
                                System.out.println(piece.type + "-> targetMove: " + targetMove.getCol()+":"+targetMove.getRow());

                                if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                    piece.col = targetMove.getCol();
                                    piece.row = targetMove.getRow();
//                                    if (GamePanel.pieceAndMoves.containsKey(piece) && GamePanel.pieceAndMoves.get(piece).getCol()!=piece.col||
//                                            GamePanel.pieceAndMoves.get(piece).getRow()!=piece.row){
//                                        GamePanel.activeP = piece;
//                                        GamePanel.pieceAndMoves.put(piece,targetMove);
//                                        foundMove = true;
//                                    }
                                    GamePanel.activeP = piece;
                                    movesMadeSoFar.add(targetMove);
                                    foundMove = true;

                                }
                                // no legal move -> not possible!
                            }
                        }
                    }
                    else {
                        System.out.println("checking for possible pawn move..");

                        // pawn move
                        for (Piece piece: GamePanel.simPieces){
                            if (piece.type==Type.PAWN&&piece.color==GamePanel.BLACK&&piece.col>2&&piece.col<5){
                                targetMove = findMostValuableSquare(piece);
                                System.out.println(piece.type + "-> targetMove: " + targetMove.getCol()+":"+targetMove.getRow());

                                if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                    piece.col = targetMove.getCol();
                                    piece.row = targetMove.getRow();
//                                    if (GamePanel.pieceAndMoves.containsKey(piece) &&GamePanel.pieceAndMoves.get(piece).getCol()!=piece.col||
//                                            GamePanel.pieceAndMoves.get(piece).getRow()!=piece.row){
//                                        GamePanel.activeP = piece;
//                                        GamePanel.pieceAndMoves.put(piece,targetMove);
//                                        foundMove = true;
//                                    }

                                    GamePanel.activeP = piece;
                                    movesMadeSoFar.add(targetMove);
                                    foundMove = true;


                                }
                                // no legal move -> not possible!
                            }
                            else if (piece.type==Type.PAWN&&piece.color==GamePanel.BLACK&&piece.col>1&&piece.col<6){
                                targetMove = findMostValuableSquare(piece);
                                System.out.println(piece.type + "-> targetMove: " + targetMove.getCol()+":"+targetMove.getRow());

                                if (targetMove.getCol()!=0&&targetMove.getRow()!=0){
                                    piece.col = targetMove.getCol();
                                    piece.row = targetMove.getRow();

                                    GamePanel.activeP = piece;
                                    movesMadeSoFar.add(targetMove);
                                    foundMove = true;


                                }
                                // no legal move -> not possible!
                            }
                        }
                    }
                }
            }
            // middle game to end game part
            else {
                continue;
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
            if (piece.equals(pieceAI)){
                for (int col=0;col<8;col++){
                    for (int row=0;row<8;row++){
//                        System.out.println(piece.type +"-> " + piece.col+": " + piece.row + " ||"+ col + " :" + row);
//                        System.out.println(piece.canMove(col,row));
                        if (pieceAI.canMove(col,row)&&(pieceAI.col!=col||pieceAI.row!=row)){
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
            System.out.println(pieceAI.type + "-> " + move.getCol() + " : " + move.getRow());
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
                        if (canCapture(pieceAI)){
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.col== move.getCol()&& whitePiece.row== move.getRow()
                                        &&pieceAI.canMove(whitePiece.col, whitePiece.row)&&getPieceValue(pieceAI)<getPieceValue(whitePiece)){
                                    return move;
                                }
                            }
                        }
                        if (move.getCol()>0&&move.getCol()<7&&move.getRow()==1||move.getRow()==6){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        // if none of the enemy pieces threats the square
                                        return move;
                                    }
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    // if at least one of the enemy pieces threats the square
                                                    // but also at least one of your pieces threats the square
                                                    return move;
                                                }
                                            }
                                        }
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
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    return move;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                case KNIGHT:
                    possibleMoves = showLegalMoves(pieceAI);
                    for (Move move: possibleMoves){
                        if (canCapture(pieceAI)){
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.col== move.getCol()&& whitePiece.row== move.getRow()
                                        &&pieceAI.canMove(whitePiece.col, whitePiece.row)&&getPieceValue(pieceAI)<getPieceValue(whitePiece)){
                                    return move;
                                }
                            }
                        }
                        if (move.getCol()>2&&move.getCol()<5&&move.getRow()>2&&move.getRow()<5){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    return move;
                                                }
                                            }
                                        }
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
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    return move;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
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
                        if (canCapture(pieceAI)){
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.col== move.getCol()&& whitePiece.row== move.getRow()
                                        &&pieceAI.canMove(whitePiece.col, whitePiece.row)&&getPieceValue(pieceAI)<getPieceValue(whitePiece)){
                                    return move;
                                }
                            }
                        }
                        if (move.getCol()>2&&move.getCol()<5&&move.getRow()>2&&move.getRow()<5){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    return move;
                                                }
                                            }
                                        }
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
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    return move;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color==pieceAI.color){
                                    if (piece.canMove(move.getCol(), move.getRow())){
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
                        if (canCapture(pieceAI)){
                            for (Piece whitePiece: GamePanel.simPieces){
                                if (whitePiece.col== move.getCol()&& whitePiece.row== move.getRow()
                                        &&pieceAI.canMove(whitePiece.col, whitePiece.row)&&getPieceValue(pieceAI)<getPieceValue(whitePiece)){
                                    return move;
                                }
                            }
                        }
                        if (move.getCol()>1&&move.getCol()<6&&move.getRow()>1&&move.getRow()<4){
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    return move;
                                                }
                                            }
                                        }
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
                                    else {
                                        for (Piece piece2 : GamePanel.simPieces) {
                                            if (piece2.color == pieceAI.color) {
                                                if (piece2.canMove(move.getCol(), move.getRow())) {
                                                    return move;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
                                        return move;
                                    }
                                }
                            }
                        }
                    }
                case QUEEN:
                    possibleMoves = showLegalMoves(pieceAI);
                    for (Move move: possibleMoves)
                    {
//                        if (canCapture(pieceAI)){
//
//                        }
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
                        else {
                            for (Piece piece: GamePanel.simPieces){
                                if (piece.color!=pieceAI.color){
                                    if (!piece.canMove(move.getCol(), move.getRow())){
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
    public static boolean canCapture(Piece pieceAI){
        for (Piece pieceW: GamePanel.simPieces){
            if (pieceW.color!=pieceAI.color){
                if (pieceAI.canMove(pieceW.col,pieceW.row)){
                }
                    return true;
                }
            }
        return false;
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