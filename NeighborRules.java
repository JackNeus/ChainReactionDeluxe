
public class NeighborRules {
    public int dx[];
    public int dy[];

    public NeighborRules(int dx[], int dy[]) {
        if (dx.length != dy.length) {
            throw new IllegalArgumentException();
        }
        this.dx = dx;
        this.dy = dy;
    }
}