
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
        if (contents.shouldExplode()) {
            contents.explode();
            Iterable<Cell> neighbors = context.getNeighbors(row, col);
            for (Cell neighbor : neighbors) {
                neighbor.addBlob(player);
            }
        }
    }

    public void addBlob(int fromPlayer) { // For use in chain reactions when a cell hasn't yet been initialized
        if (!isOccupied()) {
            initializePlayerBlob(fromPlayer);
        }
        addBlob();
    }

    public static void main(String[] args) {

    }
}