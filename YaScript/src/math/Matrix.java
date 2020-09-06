package math;

class Matrix{
  final float[][] val;
  public final int rows;
  public final int cols;
  
  public Matrix(int rows, int cols){
    this.rows = rows;
    this.cols = cols;
    val = new float[rows][cols];
  }
  
  public Matrix(float a, float b, float c, float d){
    this.rows = 2;
    this.cols = 2;
    val = new float[rows][cols];
    val[0][0]=a;
    val[0][1]=b;
    val[1][0]=c;
    val[1][1]=d;
  }
  public Matrix(int cols,float[] nums){
    if(nums.length%cols!=0){
      throw new IllegalArgumentException("This is not a full Matrix");
    }
    this.rows = nums.length/cols;
    this.cols = cols;
    val = new float[rows][cols];
    for(int i = 0; i < nums.length; i++){
      val[i/cols][i%cols] = nums[i];
    }
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
  
  public Matrix scale(Float s){
    Matrix a = this;
    Matrix r = new Matrix(rows, cols);
    for(int row = 0; row < rows; row++){
      for(int col = 0; col < cols; col++){
        r.val[row][col] = s * a.val[row][col];
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
  
  public float get(int row, int col){
    return val[row][col]; 
  }
}
