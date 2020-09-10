package game.entities;


import java.awt.Color;

import math.Matrix;
import math.Vector;
import math.Util;


/**
 *  Simple Wall.
 */
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

    /**
     * Calculates start and end of Wall
     * @param point 0 for first point, 1 for second point
     * @return location of point
     */
    public Vector get(int point) {
        if (point < 0 || point > 1) {
            throw new IllegalArgumentException("Not a valid point, must be 0 or 1, was: " + point);
        }
        Vector v = new Vector(Util.signum(point)*length / 2, 0);
        return Matrix.rotationMatrix2d(rotation).vMult(v).add(center);
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

	@Override
	public Color getColor() {
		
		return Color.CYAN;
	}
}
