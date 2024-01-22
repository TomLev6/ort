public abstract class Player {
    private boolean whiteSide;
    private boolean humanPlayer;
    private Board board; // Reference to the board

    public Player(boolean whiteSide, boolean humanPlayer, Board board) {
        this.whiteSide = whiteSide;
        this.humanPlayer = humanPlayer;
        this.board = board;
    }

    public boolean isWhiteSide() {
        return this.whiteSide;
    }

    public boolean isHumanPlayer() {
        return this.humanPlayer;
    }

    // Method to make a move
    public boolean makeMove(int startX, int startY, int endX, int endY) {
        try {
            Spot startBox = board.getBox(startX, startY);
            Spot endBox = board.getBox(endX, endY);
            Move move = new Move(this, startBox, endBox, board);
            return board.getGame().makeMove(move, this);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
