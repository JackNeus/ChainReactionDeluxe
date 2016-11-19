/*
This class will provide a common base on which different types of entities can be built.

*/

public abstract class Entity {

    public abstract void addBlob();
    public abstract boolean shouldExplode();
    public abstract void explode();
}