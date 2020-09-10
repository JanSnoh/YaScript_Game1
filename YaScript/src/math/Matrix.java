package math;

import java.io.InvalidObjectException;

/**
 * Implementation of Matrix calculus.
 */
public class Matrix {
    private final double[][] val;
    public final int rows;
    public final int cols;

    /**
     * Creates a zero matrix with given dimensions.
     *
     * @param rows rows
     * @param cols columns
     */
    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Matrices have positive size");
        }
        this.rows = rows;
        this.cols = cols;
        val = new double[rows][cols];
    }

    /**
     * Creates a 2x2 matrix with the given values
     *
     * @param d upper left
     * @param e upper right
     * @param f bottom left
     * @param g bottom right
     */
    public Matrix(double d, double e, double f, double g) {
        this.rows = 2;
        this.cols = 2;
        val = new double[rows][cols];
        val[0][0] = d;
        val[0][1] = e;
        val[1][0] = f;
        val[1][1] = g;
    }

    /**
     * Creates a matrix from an array filling a row after the other.
     *
     * @param cols number of entries per row
     * @param nums values
     */
    public Matrix(int cols, double[] nums) {
        if (cols <= 0 || nums.length == 0) {
            throw new IllegalArgumentException("Matrices have positive sizes");
        }
        if (nums.length % cols != 0) {
            throw new IllegalArgumentException("This is not a full Matrix");
        }
        this.rows = nums.length / cols;
        this.cols = cols;
        val = new double[rows][cols];
        for (int i = 0; i < nums.length; i++) {
            val[i / cols][i % cols] = nums[i];
        }
    }

    /**
     * Creates a 2d rotation matrix rotating by the given angle.
     * @param angle rotation angle
     * @return rotation matrix
     */
    public static  Matrix rotationMatrix2d(double angle) {
        return new Matrix(Math.cos(angle),- Math.sin(angle),Math.sin(angle),Math.cos(angle));
    }

    /**
     * Adds two matrizes element wise.
     * @param summand second matrix
     * @return result
     */
    public Matrix add(Matrix summand){
        if (summand == null) {
            throw new NullPointerException("Matrix summand must exist");
        }
        if(this.rows !=summand.rows || this.cols != summand.cols){
            throw new IllegalArgumentException("Matrices must be of the same size");
        }
        Matrix a = this;
        Matrix r = new Matrix(rows, cols);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                r.val[row][col] = a.val[row][col] + summand.val[row][col];
            }
        }
        return r;
    }

    /**
     * Subracts the second Matrix from the first one.
     * @param minuend second matrix
     * @return result
     */
    public Matrix sub(Matrix minuend){
        if (minuend == null) {
            throw new NullPointerException("minuend must not be null");
        }
        return add(minuend.scale(-1.0));
    }

    /**
     * Multiplies all values with a scalar.
     * @param factor scaling factor
     * @return scaled matrix
     */
    public Matrix scale(double factor){
        Matrix a = this;
        Matrix r = new Matrix(rows, cols);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                r.val[row][col] = factor * a.val[row][col];
            }
        }
        return r;
    }

    /**
     * Matrix cross multiplication.
     * @param multiplicand second matrix
     * @return cross product matrix
     */
    public Matrix mult(Matrix multiplicand){
        if (multiplicand == null) {
            throw new NullPointerException("multiplicand must not be null");
        }
        Matrix a = this;
        if(a.cols != multiplicand.rows){
            throw new IllegalArgumentException("rows must match cols");
        }
        Matrix r = new Matrix(a.rows, multiplicand.cols);
        for(int row = 0; row < a.rows; row++){
            for(int col = 0; col < multiplicand.cols; col++){
                float sum = 0;
                for(int i = 0; i < a.cols; i++){
                    sum +=a.val[row][i] * multiplicand.val[i][col];
                }
                r.val[row][col] = sum;
            }
        }
        return r;
    }

    /**
     * Transposes the matrix.
     * @return transposed matrix.
     */
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

    /**
     * Calculates determinant of Matrix.
     * @return determinant
     */
    public double determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Must be a square Matrix");
        }
        if (rows == 1) {
            return get(0, 0);
        }else if (rows == 2) {
            return get(0, 0) * get(1, 1) - get(1, 0) * get(0, 1);
        } else {
            double result = 0;
            for (int column = 0; column < cols; column++) {
                result += Util.signum(column) * get(0, column) * submatrix(0, column).determinant();
            }
            return result;
        }

    }

    /**
     * If possible creates an inverse of the matrix. Otherwise throws exception.
     * @return inverse Matrix
     */
    public Matrix inverse() {
        double det = determinant();
        if (det == 0) {
            throw new IllegalArgumentException("Matrix not invertible");
        }
        Matrix cof = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cof.set(row, col, Util.signum(row + col) * submatrix(row, col).determinant());
            }
        }
        return cof.transpose().scale(1 / det);
    }

    /**
     * Deletes a Row and or Column from a Matrix.
     * @param deleteRow row to delete or -1 if none should be deleted
     * @param deleteCol column to delete or -1 if none should be deleted
     * @return a smaller Matrix without specified row and column
     */
    public Matrix submatrix(int deleteRow, int deleteCol) {
        if (deleteRow < -1 || deleteRow >= rows) {
            throw new IllegalArgumentException("Illegal row to delete");
        }
        if (deleteCol < -1 || deleteCol >= cols) {
            throw new IllegalArgumentException("Illegal column to delete");
        }
        Matrix r = new Matrix(deleteRow == -1 ? rows : rows - 1, deleteCol == -1 ? cols : cols - 1);
        int vrow = 0;
        for (int row = 0; row < r.rows; row++) {
            int vcol = 0;
            for (int col = 0; col < r.cols; col++) {
                if (vrow == deleteRow) {
                    vrow++;
                }
                if (vcol == deleteCol) {
                    vcol++;
                }
                assert (vrow < rows || vcol < cols);
                r.set(row, col, get(vrow, vcol));
                vcol++;
            }
            vrow++;
        }
        return r;
    }

    /**
     * Multiply matrix with vector
     * @param vector vector to multiply with.
     * @return resulting Vector
     */
    public Vector vMult(Vector vector){
        return (Vector) mult(vector);
    }
    /**
     * Sets a value of specific entry.
     * @param row row of entry
     * @param col column of entry
     * @param a new entry
     */
    void set(int row, int col, double a) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Entry (" + row + ","+col + ") does not exist in this matrix");
        }
        val[row][col] = a;
    }

    /**
     * Get the value of a specific entry
     * @param row row of entry
     * @param col column of entry
     * @return value of entry
     */
    public double get(int row, int col){
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Entry (" + row + ","+col + ") does not exist in this matrix");
        }
        return val[row][col];
    }

    /**
     * Counts rows of matrix
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Counts columns of matrix.
     * @return number of columns
     */
    public int getCols() {
        return cols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ((Math.round(get(row, col) * 100.0) / 100.0)>= 0) {
                    sb.append(' ');
                }
                sb.append(Math.round(get(row, col) * 100.0) / 100.0).append(' ');
            }
            //sb.append('\n');
        }
        return sb.toString();
    }
    
    public Vector toVector() {
    	if(cols!=1) {
            throw new IndexOutOfBoundsException("Entry (" +  "," + ") does not exist in this matrix");
    	}
    	else if(rows==2) return new Vector(val[0][0],val[1][0]);
    	
    	Vector v = new Vector(rows);
    	for(int i=0;i<rows;i++) v.set(i, 0, val[i][0]);
    	return v;
    	
    }
    
}
