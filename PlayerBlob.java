import java.awt.*;

public class PlayerBlob extends Entity {
    private int size;
    private int max_size;

    public PlayerBlob(int max_size) {
        super();
        this.size = 0;
        this.max_size = max_size;
    }

    public int size() {
        return size;
    }

    public void addBlob() {
        size++;
    }

    public void explode() {
        setPlayer(-1);
        size = 0;
    }

    public boolean shouldExplode() {
        return size == max_size;
    }

    public void render(Graphics g, int cellSize, int r, int c) {
        if (size == 1) {
            int margin = cellSize / 8;
            int adjCellSize = cellSize - 2 * margin;
            g.fillOval(c + margin, r + margin, adjCellSize, adjCellSize);
        }
        if (size == 2) {
            int margin = cellSize / 10;
            int adjCellSize = (cellSize - 2 * margin) / 2;
            g.fillOval(c + margin, r + margin, adjCellSize, adjCellSize);
            g.fillOval(c + (cellSize / 2), r + (cellSize / 2), adjCellSize, adjCellSize);
        }
        if (size == 3) {
            int margin = cellSize / 10;
            int adjCellSize = (cellSize - 2 * margin) / 2;
            g.fillOval(c + (cellSize / 2 - adjCellSize / 2), r + margin, adjCellSize, adjCellSize);
            g.fillOval(c + margin, r + margin * 6, adjCellSize, adjCellSize);
            g.fillOval(c + margin * 6, r + margin * 6, adjCellSize, adjCellSize);
        }
    }
}