package game.entities;


import game.WorldEntity;
import math.Matrix;
import math.Vector;
import math.Util;


public class Wall extends WorldEntity {

    public Wall(Vector center, double length, double rotation) {
        this.length = length;
        this.rotation = rotation;
        this.center = center;
    }

    private Vector center;
    private double length;
    private double rotation;

    public double getRotation() {
        return rotation;
    }

    public double getLength() {
        return length;
    }

    public Vector getCenter() {
        return center;
    }

    @Override
    public double getDist(Vector point) {
        Vector tpoint =  Matrix.rotationMatrix2d(rotation).inverse().vMult(point.sub(center));
        double x = tpoint.get(0);
        double y = tpoint.get(1);
        if (Math.abs(x) <= length / 2) {
            return Math.abs(y);
        } else {
            return Util.dist(Math.abs(x), y, length / 2, 0);
        }

    }
}
