/*
This class will provide a common base on which different types of entities can be built.

*/

import java.awt.*;

public abstract class Entity {
    private static final NeighborRules neighborRules = 
                                new NeighborRules(new int[]{1, -1, 0, 0}, 
                                                  new int[]{0, 0, 1, -1});
    private int playerId;

    public Entity() {
        playerId = -1;
    }

    public int getPlayer() {
        return playerId;
    }

    public void setPlayer(int playerId) {
        this.playerId = playerId;
    }

    public abstract void addBlob();
    public abstract boolean shouldExplode();
    public abstract void explode(); 

    public NeighborRules getNeighborRules() {
        return neighborRules;
    }

    public abstract void render(Graphics g, int cellSize, int r, int c);
}