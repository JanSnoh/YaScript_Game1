package game.entities;

import math.Vector;

/**
 * A simple circle nothing more.
 */
public class Circle extends WorldEntity {
    Vector center;
    double radius;

    /**
     * Creates a Circle
     *
     * @param center duh
     * @param radius duh
     */
    public Circle(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getDist(Vector point) {
        return point.sub(center).length() - radius;
    }

}
