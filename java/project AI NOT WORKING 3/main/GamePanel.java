package main;
import piece.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    // for th ai part
    public static int totalMoves = 1;
    public static ArrayList<Move> moves= new ArrayList<>(); // list of all of the moves
    public static ArrayList<Move> simMoves= new ArrayList<>();
    public static Map<Piece,Move> pieceAndMoves = new HashMap<>(); // piece and its last move
    boolean notRepeatedMove=true;

    // pieces
    public static ArrayList<Piece> pieces = new ArrayList<>(); // backup list in case we want to reset
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    ArrayList<Piece> promoPieces = new ArrayList<>();
    Piece checkingP;
    public static Piece castlingP, threatendAIPiece,activeP;

    // color
    public static final int WHITE=0;
    public static final int BLACK=1;
    int currentColor = WHITE;

    // booleans
    boolean canMove;
    boolean validSquare;
    boolean promotion;
    boolean gameover;
    boolean stalemate;
    public static boolean treatendAIPieceIsDefended;

    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);

        // mouse - detect mouse actions
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces,simPieces);

    }
    public void launchGame(){
        gameThread = new Thread(this); // execute the object him self
        gameThread.start(); // calling the 'run' method
    }
    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
        target.clear();
        target.addAll(source);
    }

    public void setPieces() {
        // white pieces
        pieces.add(new Pawn(WHITE,0,6));
        pieces.add(new Pawn(WHITE,1,6));
        pieces.add(new Pawn(WHITE,2,6));
        pieces.add(new Pawn(WHITE,3,6));
        pieces.add(new Pawn(WHITE,4,6));
        pieces.add(new Pawn(WHITE,5,6));
        pieces.add(new Pawn(WHITE,6,6));
        pieces.add(new Pawn(WHITE,7,6));
        pieces.add(new Knight(WHITE,1,7));
        pieces.add(new Knight(WHITE,6,7));
        pieces.add(new Bishop(WHITE,2,7));
        pieces.add(new Bishop(WHITE,5,7));
        pieces.add(new Rook(WHITE,7,7));
        pieces.add(new Rook(WHITE,0,7));
        pieces.add(new King(WHITE,4,7));
        pieces.add(new Queen(WHITE,3,7));

        // black pieces
        pieces.add(new Pawn(BLACK,0,1));
        pieces.add(new Pawn(BLACK,1,1));
        pieces.add(new Pawn(BLACK,2,1));
        pieces.add(new Pawn(BLACK,3,1));
        pieces.add(new Pawn(BLACK,4,1));
        pieces.add(new Pawn(BLACK,5,1));
        pieces.add(new Pawn(BLACK,6,1));
        pieces.add(new Pawn(BLACK,7,1));
        pieces.add(new Knight(BLACK,1,0));
        pieces.add(new Knight(BLACK,6,0));
        pieces.add(new Bishop(BLACK,2,0));
        pieces.add(new Bishop(BLACK,5,0));
        pieces.add(new Rook(BLACK,7,0));
        pieces.add(new Rook(BLACK,0,0));
        pieces.add(new King(BLACK,4,0));
        pieces.add(new Queen(BLACK,3,0));



    }

    @Override
    public void run() {
        // Game Loop
        /*
        the game loop is a sequence of processes that run continuously
         as long as the game is running.
        here we use 'System.nanoTime()' to measure the elapsed time and
         call update and repaint methods once every 1/60 of a second.
         */
        double drawInterval = 1000000000 / (double)FPS;
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime =currentTime;

            if (delta>=1){
                update();
                repaint(); // this is calling the 'paintComponent' method
                delta--;
            }

        }
    }
    private void update(){
        if (promotion){
            promoting();
        }
        else if (!gameover && !stalemate){
            if (currentColor==BLACK){
                System.out.println("Black Turn..");
                moveAiPieceSimulation();
            }
            else{
                // if white turn
                if (mouse.pressed) {
                    if (activeP == null) {
                        for (Piece piece : simPieces) {
                            if (piece.color == currentColor &&
                                    piece.col == mouse.x / Board.SQUARE_SIZE &&
                                    piece.row == mouse.y / Board.SQUARE_SIZE) {
                                // checks if the player mouse is on the piece
                                // then we set the piece as active piece
                                activeP = piece;
                            }
                        }
                    } else {
                        simulate();
                    }
                }
                // mouse button released
                if (!mouse.pressed) {
                    if (activeP != null) {
                        if (validSquare) {
                            // move confirmed

                            // update the piece list in case a piece has been captured
                            // and removed during the simulation

                            // saves the confirmed move
//                            Move move = new Move(activeP.col, activeP.row);
//                            simMoves.add(move);

                            copyPieces(simPieces, pieces);
                            activeP.updatePosition();
                            // updates the castling piece
                            if (castlingP != null) {
                                castlingP.updatePosition();
                            }
                            if ((isKingInCheck()&& isCheckMate())||isKingOfTheHill()){

                                gameover = true;
                            }
                            else if (isStalemate()&&!isKingInCheck()){
                                // TODO: check if there is a stalemate with more than one piece
                                stalemate = true;
                            }
                            else {
                                // the game is still going
                                if (canPromote()) {
                                    promotion = true;

                                } else changePlayer();
                                // change the turn the other player after the move have been made
                            }
                        } else {
                            // the move is not valid, reset everything
                            copyPieces(simPieces, pieces);
                            activeP.resetPosition();
                            activeP = null;
                        }

                    }
                }
            }

        }

    }
    public void simulate(){

        canMove = false;
        validSquare = false;

        // reset the piece list in every loop
        // this is basically for restoring thr removed piece during the simulation
        copyPieces(pieces,simPieces);

        // reset the castling piece's position
        if (castlingP != null){
            castlingP.col= castlingP.preCol;
            castlingP.x = castlingP.getX(castlingP.col);
            castlingP = null;
        }
        else {
            // if piece is being held, update its position
            activeP.x = mouse.x-Board.HALF_SQUARE_SIZE;
            activeP.y = mouse.y-Board.HALF_SQUARE_SIZE;
            activeP.col = activeP.getCol(activeP.x);
            activeP.row = activeP.getRow(activeP.y);

            // check if the piece is hovering over a reachable square
            if (activeP.canMove(activeP.col,activeP.row)){
                canMove = true;

                // if hitting a piece , remove it from the list
                if (activeP.hittingP!=null){
                    simPieces.remove(activeP.hittingP.getIndex());
                }
                checkCastling();

                if (!isIllegal(activeP)&&!opponentCanCaptureKing()){
                    validSquare = true;
                }
            }
        }
    }
    private void moveAiPieceSimulation(){
        // TODO: remove duplicates moves (moves that repeat them self,(getting same col and row))
        // TODO: when capturing a piece remove it
        canMove = false;
        validSquare = false;
        copyPieces(pieces,simPieces);
        System.out.println("finding next move..");
        ChessAI.findMove();

        System.out.println(activeP.canMove(activeP.col,activeP.row));
        if (activeP.canMove(activeP.col,activeP.row))
        {
            System.out.println("generate the move:  " + activeP.col + ":" + activeP.row + " for: " + activeP.type + "   move number: " + totalMoves);
            canMove = true;
            // if hitting a piece , remove it from the list
            System.out.println(activeP.hittingP);
            if (activeP.hittingP!=null){
                System.out.println("hitting a piece");
                simPieces.remove(activeP.hittingP.getIndex());
                activeP.hittingP.col = 100;
                activeP.hittingP.row = 100;
                activeP.hittingP.x = 10000;
                activeP.hittingP.y = 10000;
            }
            checkCastling();

            if (!isIllegal(activeP)&&!opponentCanCaptureKing()){
                validSquare = true;
            }
            //// update part ////
            if (validSquare){
                pieceAndMoves.put(activeP,ChessAI.targetMove); // adds the last made move and the piece who made it.
                copyPieces(pieces,simPieces);
                activeP.updatePosition();
                // updates the castling piece
                if (castlingP != null) {
                    castlingP.updatePosition();
                }
                if ((isKingInCheck()&& isCheckMate())||isKingOfTheHill()){

                    gameover = true;
                }
                else if (isStalemate()&&!isKingInCheck()){
                    // TODO: check if there is a stalemate with more than one piece
                    stalemate = true;
                }
                else {
                    // the game is still going
                    if (canPromote()) {
                        promotion = true;

                    }
                    else changePlayer();
                    // change the turn the other player after the move have been made
                }
            }
            else {
                // the move is not valid, reset everything
                copyPieces(simPieces, pieces);
                activeP.resetPosition();
                activeP = null;
            }
        }
//          everyPieceMoves.put(activeP,ChessAI.targetMove);
    }
    private boolean isIllegal(Piece king){
        if (king.type==Type.KING){
            for (Piece piece:simPieces){
                if (piece!=king&&piece.color!= king.color&&piece.canMove(king.col,king.row)){
                    // checking if there is a piece that threatens the king
                    return true;
                }
            }
        }
        return false;
    }
    private boolean opponentCanCaptureKing(){
        // getting our king
        Piece king = getKing(false);
        for (Piece piece: simPieces){
            if (piece.color!=king.color&&piece.canMove(king.col,king.row)){
                // if true it means that our king is in dangerous
                return true;
            }
        }
        return false;
    }
    private boolean isKingInCheck(){

        Piece king = getKing(true);// getting the opponent king
        if (activeP.canMove(king.col,king.row)){
            // if true it means that our piece is checking the opponent king
            checkingP = activeP;
            return true;
        }
        else {
            checkingP = null;
        }

        return false;
    }
    private Piece getKing(boolean opponent){
        // if opponent = true return the opponent king else,
        // return your own king
        Piece king = null;
        for (Piece piece: simPieces){
            // find the king in the simPieces and return it
            if (opponent){
                if (piece.type==Type.KING&&piece.color!=currentColor){
                    king = piece;
                }
            }
            else {
                if (piece.type==Type.KING&&piece.color==currentColor){
                    king = piece;
                }
            }
        }
        return king;
    }
    private boolean isKingOfTheHill(){
        Piece king = getKing(false); // getting our king
        // if the opponent king is in the middle squares,
        // he won
        return king.col > 2 && king.col < 5 && king.row > 2 && king.row < 5;
    }
    private boolean isCheckMate(){
        Piece king = getKing(true); // getting the opponent king
        if (kingCanMove(king)){
            return false;
        }
        else {

            // there is no a square that the king can move to,
            // but there is still a chance , check if you can block the
            // attack with your piece

            // check the position of the checking piece and the in check
            // now we're checking what check do we have , diagonal or straight line
            // or knight attack

            int colDiff = Math.abs(checkingP.col-king.col);
            int rowDiff = Math.abs(checkingP.row-king.row);

            // with this information we find out if we can move a piece into a square between the attacking
            // piece and the king

            // we need to find out the attacking path
            if (colDiff==0){
                // the checking piece is attacking vertically
                // on the same col / same x
                if (checkingP.row<king.row){
                    // the checking piece is above the king
                    for (int row = checkingP.row;row<king.row;row++){
                        for (Piece piece: simPieces){
                            if (piece!= king&& piece.color != currentColor && piece.canMove(checkingP.col,row)){
                                // we're checking for opponent piece that threatens our king
                                // and her row
                                return false;
                            }
                        }
                    }
                }
                if (checkingP.row > king.row){
                    // the checking piece is below the king
                    for (int row = checkingP.row;row>king.row;row--){
                        for (Piece piece: simPieces){
                            if (piece!= king&& piece.color != currentColor && piece.canMove(checkingP.col,row)){
                                // we're checking for opponent piece that threatens our king
                                // and her row
                                return false;
                            }
                        }
                    }
                }
            }
            else if (rowDiff==0){
                // the checking piece is attacking horizontally
                // on the same row / same y
                if (checkingP.col < king.col){
                    // the checking piece is to the left
                    for (int col = checkingP.col;col<king.col;col++){
                        for (Piece piece: simPieces){
                            if (piece!= king&& piece.color != currentColor && piece.canMove(col,checkingP.row)){
                                // we're checking for opponent piece that threatens our king
                                // and its col
                                return false;
                            }
                        }
                    }
                }
                if (checkingP.col > king.col){
                    // the checking piece is to the right
                    for (int col = checkingP.col;col>king.col;col--){
                        for (Piece piece: simPieces){
                            if (piece!= king&& piece.color != currentColor && piece.canMove(col,checkingP.row)){
                                // we're checking for opponent piece that threatens our king
                                // and its col
                                return false;
                            }
                        }
                    }
                }

            }
            else if (colDiff==rowDiff){
                // the checking piece is attacking diagonally
                // on the different col and different row
                // total 4 possibilities: upper left, lower left,upper right, lower right
                if (checkingP.row<king.row){
                    // the checking piece is above the king
                    if (checkingP.col < king.col){
                        // the checking piece is in the upper left
                        for (int col = checkingP.col, row = checkingP.row;col<king.col;col++,row++){
                            // we increase both col and row because its diagonal
                            for (Piece piece: simPieces) {
                                if (piece != king && piece.color != currentColor && piece.canMove(col, row)) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (checkingP.col > king.col){
                        // the checking piece is in the upper right
                        for (int col = checkingP.col, row = checkingP.row;col>king.col;col--,row++) {
                            // we change both col and row because its diagonal
                            for (Piece piece : simPieces) {
                                if (piece != king && piece.color != currentColor && piece.canMove(col, row)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                if (checkingP.row > king.row){
                    // the checking piece is below the king
                    if (checkingP.col < king.col){
                        // the checking piece is in the lower left
                        for (int col = checkingP.col, row = checkingP.row;col<king.col;col++,row--) {
                            // we increase both col and row because its diagonal
                            for (Piece piece : simPieces) {
                                if (piece != king && piece.color != currentColor && piece.canMove(col, row)) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (checkingP.col > king.col){
                        // the checking piece is in the lower right
                        for (int col = checkingP.col, row = checkingP.row;col>king.col;col--,row--) {
                            // we increase both col and row because its diagonal
                            for (Piece piece : simPieces) {
                                if (piece != king && piece.color != currentColor && piece.canMove(col, row)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    private boolean kingCanMove(Piece king){
        // simulate if there is any square the king can move to
        if (isValidMove(king,-1,-1)) return true;
        if (isValidMove(king,0,-1)) return true;
        if (isValidMove(king,1,-1)) return true;
        if (isValidMove(king,1,0)) return true;
        if (isValidMove(king,-1,0)) return true;
        if (isValidMove(king,0,1)) return true;
        if (isValidMove(king,1,1)) return true;
        if (isValidMove(king,-1,1)) return true;
        return false;
    }
    private boolean isValidMove(Piece king, int colPlus,int rowPlus){
        // we create hypothetical positions to see where the king can and can't go
        // what pieces he can catch and can't
        boolean isValidMove= false;

        // update the king's position for a second
        king.col += colPlus;
        king.row +=rowPlus;

        if (king.canMove(king.col,king.row)){
            // checking if the king can move to the king.col, king.row square
            // after every change
            if (king.hittingP != null){
                // if the king can move ,check if it's hitting any piece
                // and if it is, remove it from the list
                simPieces.remove(king.hittingP.getIndex());
            }
            // then check if the move is illegal or not
            // false if it's a safe spot
            if (!isIllegal(king)){
                isValidMove = true;
            }
        }
        // reset the king's position and restore the removed piece
        // because we need to simulate all the directions ,and
        // we cannot keep adding th colPlus and rowPlus every time

        king.resetPosition();
        copyPieces(pieces,simPieces);
        return isValidMove;

    }
    private boolean isStalemate(){

        int count =0;
        // count the number of pieces
        for (Piece piece:simPieces){
            if (piece.color!= currentColor){
                count++;
            }
        }

        // if only one piece (the king) is left
        if (count==1){
            if (!kingCanMove(getKing(true))){
                return true;
            }
        }

        return false;
    }
    private void checkCastling(){
        if (castlingP != null){
            if (castlingP.col==0){
                castlingP.col+=3;
            }
            else if (castlingP.col==7){
                castlingP.col -= 2;
            }
            castlingP.x = castlingP.getX(castlingP.col);
        }
    }
    private void changePlayer(){
        // finish the turn and changes to the other player color
        if (currentColor==WHITE){
            currentColor= BLACK;
            // reset black's two stepped status
            for (Piece piece: pieces){
                if (piece.color==BLACK){
                    piece.twoStepped = false;
                }
            }
        }
        else {
            currentColor = WHITE;
            // reset white's two stepped status
            for (Piece piece: pieces){
                if (piece.color==WHITE){
                    piece.twoStepped = false;
                }
            }
        }
        totalMoves++;
        activeP = null;
    }
    private boolean canPromote(){
        if (activeP.type==Type.PAWN){
            if (currentColor==WHITE && activeP.row==0||currentColor==BLACK && activeP.row==7){
                promoPieces.clear();
                promoPieces.add(new Rook(currentColor,9,2));
                promoPieces.add(new Knight(currentColor,9,3));
                promoPieces.add(new Bishop(currentColor,9,4));
                promoPieces.add(new Queen(currentColor,9,5));
                return true;
            }
        }
        return false;
    }
    private void promoting(){

        if (mouse.pressed){
            for (Piece piece: promoPieces){
                if (piece != null && piece.type != null && piece.col == mouse.x / Board.SQUARE_SIZE && piece.row == mouse.y / Board.SQUARE_SIZE){
                    // the mouse is on one of the promoting pieces
                    switch (piece.type) {
                        case ROOK:
                            simPieces.add(new Rook(currentColor, activeP.col, activeP.row));
                            break;
                        case KNIGHT:
                            simPieces.add(new Knight(currentColor, activeP.col, activeP.row));
                            break;
                        case BISHOP:
                            simPieces.add(new Bishop(currentColor, activeP.col, activeP.row));
                            break;
                        case QUEEN:
                            simPieces.add(new Queen(currentColor, activeP.col, activeP.row));
                            break;
                        default:
                            break;
                    }

                    simPieces.remove(activeP.getIndex());
                    copyPieces(simPieces,pieces);
                    activeP = null;
                    promotion = false;
                    changePlayer();
                }
            }
        }

    }

    public void paintComponent(Graphics g){
        /*
        paintComponent is a method in JComponent that JPanel
        inherits and is used to draw objects on the panel.
         */
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // board
        board.draw(g2);

        // pieces
        for (Piece p : simPieces) {
            p.draw(g2);
        }

        if (activeP!=null){
            if ( canMove){
                if (isIllegal(activeP)||opponentCanCaptureKing()){
                    g2.setColor(Color.gray);
                }
                else {
                    g2.setColor(Color.white);
                }
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activeP.col *Board.SQUARE_SIZE,activeP.row *Board.SQUARE_SIZE,
                        Board.SQUARE_SIZE,Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                // gives a white square to show where the piece is about to be replaced
                // only if it's a reachable square (canMove)


            }


            // draw the active piece in the end, so it won't be hidden by the board or the colored square
            activeP.draw(g2);
        }
        // status message
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("Book Antiqua", Font.PLAIN, 40));
        g2.setColor(Color.white);

        if (promotion){
            g2.drawString("Promote to..",840,150);
            for (Piece piece: promoPieces){
                g2.drawImage(piece.image,piece.getX(piece.col),piece.getY(piece.row)
                        ,Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
            }
        }
        else {
            if (currentColor==WHITE){
                g2.drawString("White's turn", 840, 550);
                if (checkingP!=null&&checkingP.color==BLACK){
                    g2.setColor(Color.red);
                    g2.drawString("The King", 840, 650);
                    g2.drawString("is in check!", 840, 700);

                }
            }
            else {
                g2.drawString("black's turn", 840, 250);
                if (checkingP!=null&&checkingP.color==WHITE) {
                    g2.setColor(Color.red);
                    g2.drawString("The King", 840, 100);
                    g2.drawString("is in check!", 840, 150);
                }
            }
        }
        if (gameover){
            // declare the of the winner
            // showing the winning color on screen
            String s = "";
            if (currentColor == WHITE){
                s = "White Wins";
            }
            else {
                s = "Black Wins";
            }
            g2.setFont(new Font("Ariel", Font.PLAIN,90));
            g2.setColor(Color.green);
            g2.drawString(s, 200, 420);
        }
        if (stalemate){
            g2.setFont(new Font("Ariel", Font.PLAIN,90));
            g2.setColor(Color.lightGray);
            g2.drawString("Stalemate", 200, 420);
        }
    }
}