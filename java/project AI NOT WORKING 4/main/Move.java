package main;

import java.util.Objects;
import java.util.Random;

public class Move {
    private int pieceInitialCol;
    private Type pieceType;
    private int col;
    private int row;
    private boolean isCapture;
    private boolean isCastling;
    private boolean castlingType; // true for king side, false for queen side
    private boolean isPromotion;
    private Type promotionPiece; // For pawn promotion
    private int colTarget;
    private int rowTarget;
    public int moveValue=0;
    public Move(int col, int row){
        this.col = col;
        this.row = row;
    }


    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColTarget() {
        return colTarget;
    }

    public void setColTarget(int colTarget) {
        this.colTarget = colTarget;
    }

    public int getRowTarget() {
        return rowTarget;
    }

    public void setRowTarget(int rowTarget) {
        this.rowTarget = rowTarget;
    }
    public void getRandomMove(){
        Random rand = new Random();
        this.colTarget = rand.nextInt(8);
        this.rowTarget = rand.nextInt(8);
    }

    public int getMoveValue() {
        return this.moveValue;
    }

    public void setMoveValue(int moveValue) {
        this.moveValue = moveValue;
    }

    @Override
    public String toString() {
        return "Move{" +
                "col=" + col +
                ", row=" + row +
                ", colTarget=" + colTarget +
                ", rowTarget=" + rowTarget +
                ", moveValue=" + moveValue +
                '}';
    }
    public String toStr(){
        return "col= " + col + ", row=" + row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return col == move.col && row == move.row && moveValue == move.moveValue;
    }

    public Type getPieceType() {
        return pieceType;
    }

    public void setPieceType(Type pieceType) {
        this.pieceType = pieceType;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public void setCapture(boolean capture) {
        isCapture = capture;
    }

    public boolean isCastling() {
        return isCastling;
    }

    public void setCastling(boolean castling) {
        isCastling = castling;
    }

    public boolean isCastlingType() {
        return castlingType;
    }

    public void setCastlingType(boolean castlingType) {
        this.castlingType = castlingType;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }

    public Type getPromotionPiece() {
        return promotionPiece;
    }

    public void setPromotionPiece(Type promotionPiece) {
        this.promotionPiece = promotionPiece;
    }

    public int getPieceInitialCol() {
        return pieceInitialCol;
    }

    public void setPieceInitialCol(int pieceInitialCol) {
        this.pieceInitialCol = pieceInitialCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row, moveValue);
    }
}
