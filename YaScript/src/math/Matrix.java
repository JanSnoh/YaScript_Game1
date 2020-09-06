package math;
import java.lang.Math;

class Matrix{
  private final double[][] val;
  public final int rows;
  public final int cols;
  
  public Matrix(int rows, int cols){
    this.rows = rows;
    this.cols = cols;
    val = new double[rows][cols];
  }
  
  public Matrix(double d, double e, double f, double g){
    this.rows = 2;
    this.cols = 2;
    val = new double[rows][cols];
    val[0][0]=d;
    val[0][1]=e;
    val[1][0]=f;
    val[1][1]=g;
  }
  public Matrix(int cols,float[] nums){
    if(nums.length%cols!=0){
      throw new IllegalArgumentException("This is not a full Matrix");
    }
    this.rows = nums.length/cols;
    this.cols = cols;
    val = new double[rows][cols];
    for(int i = 0; i < nums.length; i++){
      val[i/cols][i%cols] = nums[i];
    }
  }
  
  public static Matrix rotationMatrix2d(float angle) {
	  return new Matrix(Math.cos(angle),- Math.sin(angle),Math.sin(angle),Math.cos(angle));
  }
  
  public Matrix add(Matrix b){
    if(this.rows !=b.rows || this.cols != b.cols){
      throw new IllegalArgumentException("Matrices must be of the same size");
    }
    Matrix a = this;
    Matrix r = new Matrix(rows, cols);
    for(int row = 0; row < rows; row++){
      for(int col = 0; col < cols; col++){
        r.val[row][col] = a.val[row][col] + b.val[row][col];
      }
    }
    return r;
  }
  
  public Matrix sub(Matrix b){
    return add(b.scale(-1.0));
  }
  
  public Matrix scale(double d){
    Matrix a = this;
    Matrix r = new Matrix(rows, cols);
    for(int row = 0; row < rows; row++){
      for(int col = 0; col < cols; col++){
        r.val[row][col] = d * a.val[row][col];
      }
    }
    return r;
  }
  
  public Matrix mult(Matrix b){
    Matrix a = this;
    if(a.cols != b.rows){
      throw new IllegalArgumentException("rows must match cols");
    }
    Matrix r = new Matrix(a.rows,b.cols);
    for(int row = 0; row < a.rows; row++){
      for(int col = 0; col < b.cols; col++){
        float sum = 0;
        for(int i = 0; i < a.cols; i++){
          sum +=a.val[row][i] * b.val[i][col];
        }
        r.val[row][col] = sum;
      }
    }
    return r;
  }
  
  public Matrix transpose(){
    Matrix a = this;
    Matrix r = new Matrix(cols,rows);
    for(int row = 0; row < rows; row++){
      for(int col = 0; col < cols; col++){
        r.val[col][row] = a.val[row][col];
      }
    }
    return r;
  }
  
  void set(int row, int col, double a) {
	  val[row][col] = a;
  }
  
  public double get(int row, int col){
    return val[row][col]; 
  }
}
