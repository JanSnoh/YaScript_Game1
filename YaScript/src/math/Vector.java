package math;

/**
 * An implementation of Vectors.
 */
public class Vector extends Matrix{

    /**
     * creates a Vector of a given length
     * @param rows number of entries
     */
    public Vector(int rows){
        super(rows,1);
    }

    /**
     * Easy 2d vector
     * @param a first entry
     * @param b second entry
     */
    public Vector(double a, double b){
        super(2,1);
        set(0,0,a);
        set(1,0,b);
    }

    /**
     * Dot product of two vectors
     * @param b second vector
     * @return dot product
     */
    public double dot(Vector b){
        return this.transpose().mult(b).get(0,0);
    }



    /**
     * Get a specific entry of the vector.
     * @param i index
     * @return value of entry
     */
    public double get(int i){
        return super.get(i,1);
    }

    /**
     * Calculate Lenght of Vector
     * @return Length of Vector
     */
    public double length() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Vector addition
     * @param b Vecotr to add
     * @return sum of vectors
     */
    public Vector add(Vector b) {
        return (Vector) super.add(b);
    }

    /**
     * Vector subtraction
     * @param b vector to subract
     * @return difference of two vectors
     */
    public Vector sub(Vector b) {
        return (Vector) super.sub(b);
    }

    /**
     * Scales Vector
     * @param s factor
     * @return scaled Vector
     */
    public Vector scale(double s) {
        return (Vector) super.scale(s);
    }

}
