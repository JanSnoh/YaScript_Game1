package quickMath;

public class Vector extends Matrix {

    public Vector( double[] vals) {
        super(1, vals);
    }

    public Vector(int rows) {
        super(rows, 1);
    }

    public Vector(double e1, double e2) {
        this(new double[]{e1,e2});
    }

    public double dot(Vector other) {
        if (rows != other.rows) {
            throw new IllegalArgumentException("Vectors must have same number of entries");
        }
        double result = 0;
        for (int index = 0; index < vals.length; index++) {
            result += vals[index] * other.vals[index];
        }
        return result;
    }

    public double length() {
        return Math.sqrt(dot(this));
    }
}
