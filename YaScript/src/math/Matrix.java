package math;
import java.lang.Math;
import java.math.RoundingMode;

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
    public Matrix(int cols, float[] nums) {
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
    public static Matrix rotationMatrix2d(float angle) {
        return new Matrix(Math.cos(angle),- Math.sin(angle),Math.sin(angle),Math.cos(angle));
    }

    /**
     * Adds two matrizes element wise.
     * @param b second matrix
     * @return result
     */
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

    /**
     * Subracts the second Matrix from the first one.
     * @param b second matrix
     * @return result
     */
    public Matrix sub(Matrix b){
        return add(b.scale(-1.0));
    }

    /**
     * Multiplies all values with a scalar.
     * @param d scaling factor
     * @return scaled matrix
     */
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

    /**
     * Matrix cross multiplication.
     * @param b second matrix
     * @return cross product matrix
     */
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
                if (row == deleteRow) {
                    vrow++;
                }
                if (col == deleteCol) {
                    vcol++;
                }
                r.set(row, col, get(vrow, vcol));
                vcol++;
            }
            vrow++;
        }
        return r;
    }

    /**
     * Sets a value of specific entry.
     * @param row row of entry
     * @param col column of entry
     * @param a new entry
     */
    void set(int row, int col, double a) {
        val[row][col] = a;
    }

    /**
     * Get the value of a specific entry
     * @param row row of entry
     * @param col column of entry
     * @return value of entry
     */
    public double get(int row, int col){
        return val[row][col];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sb.append(Math.round(get(row, col) * 100.0) / 100.0).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
