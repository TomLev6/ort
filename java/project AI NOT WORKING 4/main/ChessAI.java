package main;
import piece.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessAI {
    // Constants for piece values
    private static final int PAWN_VALUE = 1;
    private static final double[][] pawnMatrix =
            {{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},
                    {0.5,1.0,1.0,-2.0,-2.0,1.0,1.0,0.5},
                    {0.5,-0.5,-1.0,0.0,0.0,-1.0,-0.5,0.5},
                    {0.0,0.0,0.0,2.0,2.0,0.0,0.0,0.0},
                    {0.5,0.5,1.0,2.5,2.5,1.0,0.5,0.5},
                    {1.0,1.0,2.0,3.0,3.0,2.0,1.0,1.0},
                    {5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0},
                    {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0}};
    private static final int KNIGHT_VALUE = 3;
    private static final double[][] knightMatrix =
            {
                    {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0},
                    {-4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0},
                    {-3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0},
                    {-3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0},
                    {-3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0},
                    {-3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0},
                    {-4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0},
                    {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}
            };
    private static final int BISHOP_VALUE = 3;
    private static final double[][] bishopMatrix =
            {
                    { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0},
                    { -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0},
                    { -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0},
                    { -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0},
                    { -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0},
                    { -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0},
                    { -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0},
                    { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0}
            };
    private static final int ROOK_VALUE = 5;
    private static final double[][] rookMatrix =
            {{  0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0},
                    { -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
                    { -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
                    { -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
                    { -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
                    { -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
                    {  0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5},
                    {  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0}};
    private static final int QUEEN_VALUE = 9;
    private static final double[][] queenMatrix = {
            { -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0},
            { -1.0,  0.0,  0.0,  0.0,  0.0,   0.5,  0.0, -1.0},
            { -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.5, -1.0},
            { -0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5},
            {  0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5},
            { -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0},
            { -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0},
            { -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0}};
    private static final int KING_VALUE = 90;
    private static final double[][] kingMatrix ={
            {  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 },
            {  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 },
            { -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0},
            { -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0},
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0}};

    public static Move targetMove = new Move(0,0);
    public static Move lastMove= new Move(0,0);;
    public static ArrayList<Move> movesMadeSoFar = new ArrayList<>();
    public static Piece AIpiece=null;
    public static boolean captureFlag = false;
    public static String openingMove = "no move";
//    private String fileData ="";

    public static String[][] boardState= new String[8][8];
//    public static boolean kingInCheck=false;

//    public static String loadFile() throws IOException {
//
//        return new String(Files.readAllBytes(Paths.get("src\\db\\1.txt")));
//
//    }
    public static String loadFile() throws IOException {

        try (Scanner scanner = new Scanner(Paths.get("src\\db\\1.txt"))) {
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine()).append("\n");
            }
            return builder.toString();
        }

    }


    public static void setFENBoard(){
        int space=0;
        for (int row=0;row<8;row++){
            for (int col=0;col<8;col++){
                for (Piece piece : GamePanel.simPieces){
                    if (piece.col==col&&piece.row==row){
                        boardState[col][row] = pieceTypeToStr(piece);
                    }
                }
            }
        }
        for (int row=0;row<8;row++){
            for (int col=0;col<8;col++) {
                if (boardState[col][row]==null){
                    space++;
                }
                else {
                    if (space!=0){
                        System.out.print(space +""+ boardState[col][row]);
                        space = 0;
                    }
                    else System.out.print(boardState[col][row]);

                }

            }
            if (space!=0){
                System.out.println(space);
                space = 0;
            }
            else System.out.println();
        }
    }
    public static String pieceTypeToStr(Piece piece){
        if (piece.type!=null){
            switch (piece.type) {
                case KING:
                    if (piece.color == GamePanel.BLACK) {
                        return "k";
                    } else return "K";

                case PAWN:
                    if (piece.color == GamePanel.BLACK) {
                        return "p";
                    } else return "P";
                case ROOK:
                    if (piece.color == GamePanel.BLACK) {
                        return "r";
                    }
                    else return "R";
                case QUEEN:
                    if (piece.color == GamePanel.BLACK) {
                        return "q";
                    } else return "Q";
                case BISHOP:
                    if (piece.color == GamePanel.BLACK) {
                        return "b";
                    } else return "B";
                case KNIGHT:
                    if (piece.color == GamePanel.BLACK) {
                        return "n";
                    } else return "N";
            }
        }
        return " ";
    }
    public static Type strToPieceType(char type){
        switch (type) {
            case 'K':
                return Type.KING;
            case 'P':
                return Type.PAWN;
            case 'R':
                return Type.ROOK;
            case 'Q':
                return Type.QUEEN;
            case 'B':
                return Type.BISHOP;
            case 'N':
                return Type.KNIGHT;
        }
        return null;
    }

    public static Move findOpeningResponse() throws IOException {

        String fileData = loadFile();
        Scanner scanner = new Scanner(fileData);
        String[] lines= new String[50];
        String[] moves= new String[50];

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(GamePanel.gameSoFar)) {
//                System.out.println(line);
                 lines = line.split(GamePanel.gameSoFar);

            }
        }

        for (String s: lines){
            if (s!=null)
                moves = s.split( " ");
            else return new Move(0,0);
        }
        System.out.println(moves[0]);
        openingMove = moves[0];
        return lineToMove(moves[0]);
    }

    public static void findMove() throws IOException {
       /*
       first checking if we can move our king one more step towards the middle squares.
       second if we can't move the king we decide what piece we should move.
       then we decide what square.
        */
        boolean foundMove = false;
        while(!foundMove) {

            if (GamePanel.totalMoves<15){
                targetMove=findOpeningResponse();

                if (!Objects.equals(openingMove, "no move")){
                    for (Piece piece : GamePanel.simPieces) {
                        System.out.println(openingMove);

//                    System.out.println(GamePanel.gameSoFar);
                        if (piece.color == GamePanel.BLACK&&piece.type==targetMove.getPieceType()) {
//                        targetMove=findOpeningResponse();
//                        System.out.println(targetMove.toStr() + " "+ targetMove.getPieceType());
                            if (piece.canMove(targetMove.getCol(), targetMove.getRow())){
//                            targetMove.setCol(2);
//                            targetMove.setRow(2);
//                            System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +piece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
                                if (captureFlag&&piece.col==targetMove.getPieceInitialCol()){
                                    GamePanel.activeP = piece;
                                    GamePanel.activeP.col = targetMove.getCol();
                                    GamePanel.activeP.row = targetMove.getRow();
                                    movesMadeSoFar.add(targetMove);
                                    captureFlag = false;
                                    foundMove = true;
                                }
                                else {
                                    GamePanel.activeP = piece;
                                    GamePanel.activeP.col = targetMove.getCol();
                                    GamePanel.activeP.row = targetMove.getRow();
                                    movesMadeSoFar.add(targetMove);
                                    foundMove = true;
                                }
                            }
                            return;
                        }
//                        if(!foundMove&&piece.canMove(4,3)){
//                            targetMove.setCol(4);
//                            targetMove.setRow(3);
//
////                            System.out.println("found move ! move value:    " + targetMove.moveValue +" ," +piece.type + " -> " + targetMove.getCol() + " : " + targetMove.getRow());
//                            GamePanel.activeP = piece;
//                            GamePanel.activeP.col = targetMove.getCol();
//                            GamePanel.activeP.row = targetMove.getRow();
//                            movesMadeSoFar.add(targetMove);
//                            foundMove = true;
//
//                       }
                    }
                }
//                System.out.println(loadFile());
//                setFENBoard();

            }

            // after openings or more move

            Move AImove = findMostValuableSquare();
            if (lastMove.equals(AImove)){
                // checking for repeation
                while (lastMove.equals(AImove)){
                    AImove = findMostValuableSquare();
                }
            }
            lastMove = AImove;
            simAIMove(AImove);
//                System.out.println("found move ! move value:    " + AImove.moveValue +" ," +AIpiece.type + " -> " + AImove.getCol() + " : " + AImove.getRow());
            movesMadeSoFar.add(AImove);
            foundMove = true;


        }
    }
    private static void simAIMove(Move aiMove){
        GamePanel.activeP = AIpiece;
        AIpiece.col = aiMove.getCol();
        AIpiece.row = aiMove.getRow();

//        GamePanel.copyPieces(pieces,simPieces);
//        GamePanel.activeP.updatePosition();

    }
    // TODO: need to add when piece can capture, and when the king is in check + change the target to checkmate



    public static Piece getKing()
    {
        for (Piece piece:GamePanel.simPieces){
            if (piece.type == Type.KING && piece.color==GamePanel.BLACK){
                return piece;
            }
        }
        return null;
    }
    public static HashMap<Piece,String> whichPieceMoved() {
        // detects which piece has been moved
        HashMap<Piece, String> MovedPiece = new HashMap<>();
        int col = GamePanel.activeP.col;
        int row = GamePanel.activeP.row;
        MovedPiece.put(GamePanel.activeP,moveToLine(col,row));
        return MovedPiece;
    }

    public static String moveToLine(int col, int row){
        String boardCol="";
        switch (col){
            case 0:
                boardCol = "a";
                break;
            case 1:
                boardCol = "b";
                break;
            case 2:
                boardCol = "c";
                break;
            case 3:
                boardCol = "d";
                break;
            case 4:
                boardCol = "e";
                break;
            case 5:
                boardCol = "f";
                break;
            case 6:
                boardCol = "g";
                break;
            case 7:
                boardCol = "h";
                break;
        }
        if (GamePanel.activeP!=null&&GamePanel.activeP.type==Type.PAWN){
            return boardCol+(8-row);
        }
        else {
            assert GamePanel.activeP != null;
            return pieceTypeToStr(GamePanel.activeP)+boardCol+(8-row);
        }
    }
    public static Move lineToMove(String line) {

        if (line.length()>6) {
            return null;
        }
        if (line.contains("x")){
            // capture
            System.out.println("Capture! \n");
            captureFlag = true;
            int blackPawnCol = Character.toLowerCase(line.charAt(0)) - 'a';
            int whitePawnCol = Character.toLowerCase(line.charAt(2)) - 'a';
            int whitePawnRow = 8 - Character.digit(line.charAt(3), 10);
            Move m = new Move(whitePawnCol, whitePawnRow);
            m.setPieceType(Type.PAWN);
            m.setPieceInitialCol(blackPawnCol);
            return m;
        }
        else if (line.contains("N")||line.contains("B")||line.contains("Q")||line.contains("R")||line.contains("K")){
            int col = Character.toLowerCase(line.charAt(1)) - 'a';
            int row = 8 - Character.digit(line.charAt(2), 10);
            Move m = new Move(col, row);
//            System.out.println(m.toStr());
            m.setPieceType(strToPieceType(line.charAt(0)));
            return m;
        }
        else {
//            System.out.println(line);
            int col = Character.toLowerCase(line.charAt(0)) - 'a';
            int row = 8 - Character.digit(line.charAt(1), 10);
            Move m = new Move(col, row);
            m.setPieceType(Type.PAWN);
            return m;
        }
    }
//    public static Move parseMove(String moveString) throws ParseException {
//        Move move = new Move(0,0);
//
//        // Handle special moves first
//        if (moveString.equals("O-O")) {
//            move.setCastling(true);
//            move.setCastlingType(true);
//        } else if (moveString.equals("O-O-O")) {
//            move.setCastling(true);
//            move.setCastlingType(false);
//        } else {
//            // Regular move parsing
//
//            // Capture flag
//            if (moveString.charAt(0) == 'x') {
//                move.setCapture(true);
//                moveString = moveString.substring(1);
//            }
//
//            // Piece type
//            move.setPieceType(Type.valueOf(moveString.substring(0, 1).toUpperCase()));
//            moveString = moveString.substring(1);
//
//            // Disambiguation (optional)
//            // ... (code to handle disambiguation if needed)
//
//            // Destination square
//            int col = moveString.charAt(0) - 'a';
//            int row = Character.digit(moveString.charAt(1), 10) - 1; // Adjust for 0-based indexing
//            move.setCol(col);
//            move.setRow(row);
//
//            // Promotion (optional)
//            if (moveString.length() > 2 && moveString.charAt(2) == '=') {
//                move.setIsPromotion(true);
//                move.setPromotionPiece(PieceType.valueOf(moveString.substring(3).toUpperCase()));
//            }
//        }
//
//        // Validate move
//        if (!move.isValid()) {
//            throw new ParseException("Invalid move: " + moveString);
//        }
//
//        return move;
//    }

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

    public static Move findBestMove() {
        Move defaultMove = new Move(0, 0);
        for (Piece blackPiece : GamePanel.simPieces) {
            if (blackPiece.color == GamePanel.BLACK && blackPiece.type != null) {
                ArrayList<Move> possibleMoves = showLegalMoves(blackPiece);
                for (Move move : possibleMoves) {

                }


            }
        }
        return defaultMove;
    }

    public static Move findMostValuableSquare() {
        // TODO: if the king is in check

        Move defaultMove = new Move(0,0);
        int maxMoveValue = 1;
        for (Piece blackPiece : GamePanel.simPieces) {
            if (blackPiece.color == GamePanel.BLACK && blackPiece.type != null) {
                ArrayList<Move> possibleMoves = showLegalMoves(blackPiece);
                for (Move move : possibleMoves) {
                    int currentMoveValue = 0;  // Initialize currentMoveValue for each move
                    Move prevPieceLocation= new Move(blackPiece.col,blackPiece.row); // saves previous location
//                    prevPieceLocation.setCol(blackPiece.col);
//                    prevPieceLocation.setRow(blackPiece.row);
                    if (kingInCheck()){
                        System.out.println("--KING IN CHECK--");

                        switch (blackPiece.type) {
                            // we rather block a check with a pawn than valuable piece
                            case PAWN:
                                AIpiece = blackPiece;
                                simAIMove(move);
                                // possible capture
                                for (Piece whitePiece: GamePanel.simPieces){
                                    if (whitePiece.color==GamePanel.WHITE){
                                        // we might, can capture
                                        if (getPieceValue(blackPiece)<getPieceValue(whitePiece)){
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                System.out.println("possible capture " + move.toStr());
                                                currentMoveValue = 7;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (!kingInCheck()){
                                    currentMoveValue = 5;
                                }
                                break;
                            case KNIGHT, BISHOP:
                                AIpiece = blackPiece;
                                simAIMove(move);
                                // possible capture
                                for (Piece whitePiece: GamePanel.simPieces){
                                    if (whitePiece.color==GamePanel.WHITE){
                                        // we might, can capture
                                        if (getPieceValue(blackPiece)<=getPieceValue(whitePiece)){
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                System.out.println(blackPiece.type + " can capture " + whitePiece.type+ " on " + move.toStr() );

                                                currentMoveValue = 6;
                                                break;

                                            }
                                        }
                                    }
                                }
                                if (!kingInCheck()){
                                    currentMoveValue = 4;
                                }

                                break;

                            case ROOK:
                                AIpiece = blackPiece;
                                simAIMove(move);
                                // possible capture
                                for (Piece whitePiece: GamePanel.simPieces){
                                    if (whitePiece.color==GamePanel.WHITE){
                                        // we might, can capture
                                        if (getPieceValue(blackPiece)<getPieceValue(whitePiece)){
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                System.out.println(blackPiece.type + " can capture " + whitePiece.type+ " on " + move.toStr() );

                                                currentMoveValue = 8;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (!kingInCheck()){
                                    currentMoveValue = 3;
                                }
                                break;
                            case KING:
                                if (isSafeSquare(move)){
                                    AIpiece = blackPiece;
                                    simAIMove(move);
                                    // possible capture
                                    for (Piece whitePiece: GamePanel.simPieces){
                                        if (whitePiece.color==GamePanel.WHITE){
                                            // we might, can capture
                                            if (5<getPieceValue(whitePiece)){
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    System.out.println(blackPiece.type + " can capture " + whitePiece.type+ " on " + move.toStr() );
                                                    currentMoveValue = 7;
                                                }
                                            }
                                            else if (3<getPieceValue(whitePiece)){
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    currentMoveValue = 4;
                                                }
                                            }
                                            else if (1<getPieceValue(whitePiece)){
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    currentMoveValue = 3;
                                                }
                                            }
                                            else{
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    currentMoveValue = 4;
                                                }
                                            }
                                        }
                                    }
                                    if (!kingInCheck()){
                                        currentMoveValue = 2;
                                    }
                                }
                                break;
                            case QUEEN:
                                AIpiece = blackPiece;
                                simAIMove(move);
                                // possible capture
                                for (Piece whitePiece: GamePanel.simPieces){
                                    if (whitePiece.color==GamePanel.WHITE){
                                        // we might, can capture
                                        if (4<getPieceValue(whitePiece)){
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                currentMoveValue = 4;
                                            }
                                        }
                                    }
                                }
                                if (!kingInCheck()){
                                    currentMoveValue = 1;
                                }
                                break;
                        }
                    }
                    else {
                        // Check piece type and update currentMoveValue accordingly
                        switch (blackPiece.type) {
                            case ROOK:
                                // possible capture
                                for (Piece whitePiece: GamePanel.simPieces){
                                    if (whitePiece.color==GamePanel.WHITE){
                                        // we might, can capture
                                        if (getPieceValue(blackPiece)<getPieceValue(whitePiece)){
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
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
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
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
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                currentMoveValue = 7;

                                            }
                                        }
                                        else if (getPieceValue(blackPiece)+3<getPieceValue(whitePiece)){
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                currentMoveValue = 6;

                                            }
                                        }
                                        else {
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
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
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                currentMoveValue = 6;

                                            }
                                        }
                                        else if (getPieceValue(blackPiece)<=getPieceValue(whitePiece)){
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                currentMoveValue = 4;

                                            }
                                        }
                                    }
                                }
                                break;
                            case KING:
                                if (isSafeSquare(move)){
                                    System.out.println("Safe square.." + move.toStr());
                                    if ((move.getCol() == 3 ||move.getCol() == 4) && (move.getRow() == 3 || move.getRow() ==4)) {
                                        // win move
                                        currentMoveValue = 9;
                                        break;
                                    }
                                    else if (move.getCol() == blackPiece.col && move.getRow() > blackPiece.row) {
                                        // move towards the middle vertically
                                        currentMoveValue = 5;
                                        break;
                                    }
                                    // possible capture
                                    for (Piece whitePiece: GamePanel.simPieces){
                                        if (whitePiece.color==GamePanel.WHITE){
                                            // we might, can capture
                                            if (5<getPieceValue(whitePiece)){
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    currentMoveValue = 7;
                                                }
                                            }
                                            else if (3<getPieceValue(whitePiece)){
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    currentMoveValue = 4;
                                                }
                                            }
                                            else if (1<getPieceValue(whitePiece)){
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    currentMoveValue = 3;
                                                }
                                            }
                                            else{
                                                if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                    currentMoveValue = 4;
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
                                            if (blackPiece.canMove(whitePiece.col, whitePiece.row)&&!kingInCheck()){
                                                currentMoveValue = 4;
                                            }
                                        }
                                    }
                                }
                                break;
                        }
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
        return defaultMove;
    }

    public static boolean kingInCheck(){
        Piece blackKing=getKing();
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
                else if (whitePiece.type==Type.PAWN){
                    // pawn check
                    if ((move.getCol()==whitePiece.col+1&&move.getRow()==whitePiece.row-1)
                            ||(move.getCol()==whitePiece.col-1&&move.getRow()==whitePiece.row-1)){
                        return false;
                    }
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