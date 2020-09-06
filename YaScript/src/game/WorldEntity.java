package game;

import math.Vector;

public abstract class WorldEntity {
    private Vector position;
    private double orientation;
    private int linkCount;

    public abstract double getDist(Vector point);

    boolean load(){
        linkCount++;
        return linkCount == 1;
    }

    boolean unload(){
        linkCount--;
        return linkCount == 0;
    }
}
