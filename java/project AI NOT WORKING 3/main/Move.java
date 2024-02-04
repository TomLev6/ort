package main;

import java.util.Random;

public class Move {
    private int col;
    private int row;
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
}

