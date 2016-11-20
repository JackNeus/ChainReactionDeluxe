import java.awt.*;

public class Cell {
    private Board context; // Board that this cell is in
    private int row;
    private int col; 

    private Entity contents; // Entity contained in cell
    private int player; // Id of player

    private int capacity; // Capacity of cell

    public Cell(Board context, int row, int col, int capacity) {
        this.context = context;
        this.row = row;
        this.col = col;

        this.capacity = capacity;
        this.player = -1;

        this.contents = null; // Start out cell empty
    }

    public Cell(Board context, int row, int col, int capacity, Entity entity) {
        this(context, capacity, row, col);
        this.contents = entity;
    }

    public boolean isOccupied() {
        return contents != null;
    }

    public int getPlayer() {
        return player;
    }

    public int getSize() { // TODO: replace with something better
        if (contents != null && contents instanceof PlayerBlob) {
            return ((PlayerBlob) contents).getSize();
        }
        return 0;
    }

    public void initializePlayerBlob(int player) {
        this.player = player;
        this.contents = new PlayerBlob(capacity);
    }

    public void addBlob() {
        contents.addBlob();
    }

    public void addBlob(int fromPlayer) { // For use in chain reactions when a cell hasn't yet been initialized
        if (!isOccupied()) {
            initializePlayerBlob(fromPlayer);
        }
        addBlob();
    }

    public void render(Graphics g, int cellSize) {
        if (contents == null) return;
        contents.render(g, cellSize, (row + 1) * cellSize, (col + 1) * cellSize);
    }

    public static void main(String[] args) {

    }
}