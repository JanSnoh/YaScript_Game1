package math;

/**
 * An implementation of Vectors.
 */
class Vector extends Matrix{

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
  public Vector(float a, float b){
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

}
