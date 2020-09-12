package quickMath;

import Util.Pair;

public class Matrix {
    final double[] vals;
    public final int cols;
    public final int rows;

    public Matrix(int cols, double[] vals) {
        if (cols <= 0) {
            throw new IllegalArgumentException("Must have at least one column");
        }
        if (vals.length % cols != 0) {
            throw new IllegalArgumentException("Number of values not valid");
        }
        this.vals=vals;
        this.cols = cols;
        this.rows = vals.length % cols;

    }

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and Cols must be greater than 0");
        }
        this. rows = rows;
        this.cols = cols;
        vals = new double[rows * cols];
    }

    public void add(Matrix summand) {
        if (!(rows == summand.rows && cols == summand.cols)) {
            throw new IllegalArgumentException("Matrix dimensions not matching");
        }
        for (int index = 0; index < vals.length; index++) {
            vals[index] += summand.vals[index];
        }
    }
    public void sub(Matrix minuend) {
        if (!(rows == minuend.rows && cols == minuend.cols)) {
            throw new IllegalArgumentException("Matrix dimensions not matching");
        }
        for (int index = 0; index < vals.length; index++) {
            vals[index] -= minuend.vals[index];
        }
    }

    public void scale(double scalar) {
        for (int index = 0; index < vals.length; index++) {
            vals[index] *= scalar;
        }
    }

    public Matrix mult(Matrix other) {
        return new Matrix(other.cols, rawMult(other));
    }

    public Vector vMult(Vector vector) {
        return new Vector(rawMult(vector));
    }

    public double[] rawMult(Matrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("this.cols must mach other.rows");
        }
        double[] result = new double[rows*other.cols];
        for (int index = 0; index < result.length; index++) {
            Pair<Integer, Integer> rowCol = getRowCol(index);
            for (int i = 0; i < cols; i++) {
                result[index] += vals[index(rowCol.getKey(),i)]*other.vals[index(i, rowCol.getValue())];
            }
        }
        return result;
    }


    public double get(int index) {
        return vals[index];
    }

    public double get(int row, int col) {
        return vals[index(row,col)];
    }
   private int index(int row, int col) {
        return row*cols + col;
   }

    private Pair<Integer,Integer> getRowCol(int index) {
        return new Pair<>(index/cols,index%cols);
    }
}
