import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, Spot> boxes;

    public Board() {
        this.boxes = new HashMap<>();
        this.resetBoard();
    }

    public Spot getBox(int x, int y) throws Exception {
        String key = getKey(x, y);

        if (!boxes.containsKey(key)) {
            throw new Exception("Index out of bound");
        }

        return boxes.get(key);
    }

    private String getKey(int x, int y) {
        return x + "," + y;
    }

    public void resetBoard() {
        // initialize white pieces
        boxes.put(getKey(0, 0), new Spot(0, 0, new Rook(true)));
        boxes.put(getKey(0, 7), new Spot(0, 7, new Rook(true)));
        boxes.put(getKey(0, 1), new Spot(0, 1, new Knight(true)));
        boxes.put(getKey(0, 6), new Spot(0, 6, new Knight(true)));
        boxes.put(getKey(0, 2), new Spot(0, 2, new Bishop(true)));
        boxes.put(getKey(0, 5), new Spot(0, 5, new Bishop(true)));
        boxes.put(getKey(0, 3), new Spot(0, 3, new King(true)));
        boxes.put(getKey(0, 4), new Spot(0, 4, new Queen(true)));

        // ... (similar for other white pieces) (pawns)!

        // initialize black pieces
        boxes.put(getKey(7, 0), new Spot(7, 0, new Rook(false)));
        boxes.put(getKey(7, 7), new Spot(7, 7, new Rook(false)));
        boxes.put(getKey(7, 1), new Spot(7, 1, new Knight(false)));
        boxes.put(getKey(7, 6), new Spot(7, 6, new Knight(false)));
        boxes.put(getKey(7, 2), new Spot(7, 2, new Bishop(false)));
        boxes.put(getKey(7, 5), new Spot(7, 5, new Bishop(false)));
        boxes.put(getKey(7, 3), new Spot(7, 3, new King(false)));
        boxes.put(getKey(7, 4), new Spot(7, 4, new Queen(false)));

        // ... (similar for other black pieces)

        // initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes.put(getKey(i, j), new Spot(i, j, null));
            }
        }
    }
}
