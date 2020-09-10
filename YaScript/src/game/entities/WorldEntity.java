package game.entities;

import java.awt.Color;

import math.Vector;

public abstract class WorldEntity {
    private Vector position;
    private double orientation;
    private int linkCount;

    public abstract double getDist(Vector point);
    public abstract Color getColor();

    public boolean load(){
        linkCount++;
        return linkCount == 1;
    }

    public boolean unload(){
        linkCount--;
        return linkCount == 0;
    }
}
