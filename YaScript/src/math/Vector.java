package math;

class Vector extends Matrix{
  
  public Vector(int rows){
    super(rows,1);
  }
  public Vector(float a, float b){
    super(2,1);
    set(0,0,a);
    set(1,0,b);
  }
  
  public double dot(Vector b){
    return this.transpose().mult(b).get(0,0);
  }
  
  public double get(int i){
    return super.get(i,1);
  }
  
}
