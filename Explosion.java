// Keeps track of individual explosions for animation purposes

import java.util.LinkedList;
import java.util.ArrayList;

public class Explosion {
    private LinkedList<CellCoords[]> explosion;

    public Explosion(){
        explosion = new LinkedList<CellCoords[]>();
    }

    public void addExplosion(CellCoords from, CellCoords to) {
        CellCoords[] coordPair = new CellCoords[]{from, to};
        explosion.add(coordPair);
    }

    public Iterable<CellCoords[]> getExplosion() {
        return explosion;
    }
}