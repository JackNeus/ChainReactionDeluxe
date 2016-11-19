
public class PlayerBlob extends Entity {
    private int size;
    private int max_size;

    public PlayerBlob(int max_size) {
        this.size = 0;
        this.max_size = max_size;
    }

    public void addBlob() {
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean shouldExplode() {
        return size == max_size;
    }

    public void explode() {
        size = 0;
    }
}