package main;
import piece.*;

public class ChessAI {
    // Constants for piece values
    private static final int PAWN_VALUE = 1;
    private static final int KNIGHT_VALUE = 3;
    private static final int BISHOP_VALUE = 3;
    private static final int ROOK_VALUE = 5;
    private static final int QUEEN_VALUE = 9;
    private static final int KING_VALUE = 90;

    public static void findNextMove(){
        Piece opponentMovedPiece = whichPieceMoved();
        Piece aiMovingPiece = GamePanel.simPieces.get(23);
        if (GamePanel.totalMoves==1){
            if (opponentMovedPiece.type == Type.PAWN){
                // let's find out which pawn have moved
                if (opponentMovedPiece.col == 3){
                    // queen pawn moved
                    for (Piece piece: GamePanel.simPieces){
                        if (piece.color==GamePanel.BLACK&&piece.row==1&&piece.col==3){
                            aiMovingPiece = piece;
                            GamePanel.activeP =  aiMovingPiece;
                        }
                    }

                }
                else if (opponentMovedPiece.col == 2){
                    // left bishop pawn
                    for (Piece piece: GamePanel.simPieces){
                        if (piece.color==GamePanel.BLACK&&piece.row==1&&piece.col==4){
                            aiMovingPiece = piece;
                            GamePanel.activeP =  aiMovingPiece;
                        }
                    }
                }
                else if(opponentMovedPiece.col == 5){
                    // right bishop pawn
                    for (Piece piece: GamePanel.simPieces){
                        if (piece.color==GamePanel.BLACK&&piece.row==1&&piece.col==3){
                            aiMovingPiece = piece;
                            GamePanel.activeP =  aiMovingPiece;
                        }
                    }
                }
            }
            if (opponentMovedPiece.type==Type.KNIGHT){
                // if the knight moved, lets check where to
                GamePanel.activeP =  aiMovingPiece;
            }
        }
        else {
            // two or more moves have been made
            for (Piece piece: GamePanel.simPieces){
                if (piece.color==GamePanel.BLACK&&piece.canMove(GamePanel.threatendAIPiece.col,GamePanel.threatendAIPiece.row)){
                    GamePanel.treatendAIPieceIsDefended = true;
                }
            }
            if (!GamePanel.treatendAIPieceIsDefended){
                // if there isn't a piece that is protecting the ai threatened piece
                // we need to move some piece to protect it
                for (Piece piece: GamePanel.simPieces){
                    // we simulate moves that can be helping us protect the piece
                    if (piece.color==GamePanel.BLACK&&piece.canMove(GamePanel.threatendAIPiece.col,GamePanel.threatendAIPiece.row)){
                        piece.col =
                        GamePanel.treatendAIPieceIsDefended = true;
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
    public static Move generateLegalMove(){

    }
    public int evaluateBoard() {
        int score = 0;

        // Evaluate material balance
        //        score += evaluateMaterial(board);

        // Add other factors such as piece mobility, pawn structure, king safety, etc.

        return score;
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
    // Implement the rest of your methods, e.g., generateMoves, generateCaptureMoves, etc.
}
