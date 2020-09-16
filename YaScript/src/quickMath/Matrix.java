package quickMath;

import Util.Pair;
import math.Util;

import static math.Util.signum;

public class Matrix {
    public final int cols;
    public final int rows;
    final double[] vals;

    public Matrix(int cols, double[] vals) {
        if (cols <= 0) {
            throw new IllegalArgumentException("Must have at least one column");
        }
        if (vals.length % cols != 0) {
            throw new IllegalArgumentException("Number of values not valid");
        }
        this.vals = vals;
        this.cols = cols;
        this.rows = vals.length / cols;

    }

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and Cols must be greater than 0");
        }
        this.rows = rows;
        this.cols = cols;
        vals = new double[rows * cols];
    }

    public static Matrix rotationMatrix2d(double angle) {
        double[] vals = {};
        return new Matrix(2, vals);
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
        double[] result = new double[rows * other.cols];
        for (int index = 0; index < result.length; index++) {
            Pair<Integer, Integer> rowCol = getRowCol(index);
            for (int i = 0; i < cols; i++) {
                result[index] += vals[index(rowCol.getKey(), i)] * other.vals[index(i, rowCol.getValue())];
            }
        }
        return result;
    }

    public double det() {
        if (cols != rows) {
            throw new IllegalArgumentException("Matrix must be square, was " + rows + "x" + cols);
        }
        return det(cols, vals);
    }

    private double det(int cs, double[] v) {
        if (cs == 1) {
            return v[0];
        } else if (cs == 2) {
            return (v[0] * v[3] - v[1] * v[2]);
        } else {
            int sum = 0;
            for (int i = 0; i < cs; i++) {
                sum += signum(i) *v[i]* det(cs - 1, submatrix(cs, v, 0, i));
            }
            return sum;
        }
    }

    private double[] submatrix(int cols, double[] v, int drow, int dcol) {
        double[] subMat = new double[(cols - 1) * (cols - 1)];
        int subindex = 0;
        for (int i = 0; i < v.length; i++) {
            if (i % cols != dcol && i / cols != drow) {
                subMat[subindex] = v[i];
                subindex++;
            }
        }
        return subMat;
    }

    public Matrix transpose() {
        return transpose(cols,vals);
    }

    public Matrix transpose(int cs, double[]v) {
        double[] nVals = new double[v.length];
        int rs = v.length/cs;
        for (int r = 0; r < rs; r++) {
            for (int c = 0; c < cs; c++) {
                nVals[index(c, r,v.length/ cs)]= v[index(r,c, cs)];
            }
        }
        return new Matrix(rows, nVals);
    }

    public Matrix inverse() {
        double det = det();
        if (det == 0) {
            throw new IllegalArgumentException(this + "\nNot invertible");
        }
        double[] cof = new double[vals.length];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cof[index(row, col)] = Util.signum(row + col) * det(cols-1, submatrix(cols, vals, row, col));
            }
        }
        Matrix result = transpose(cols, cof);
        result.scale(1.0/det);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < vals.length; i++) {
            if ((Math.round(vals[i] * 100.0) / 100.0)>= 0) {
                sb.append(' ');
            }
            sb.append(Math.round(vals[i] * 100.0) / 100.0).append(' ');
            if ((i + 1) % cols == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public double get(int index) {
        return vals[index];
    }

    public double get(int row, int col) {
        return vals[index(row, col)];
    }

    private int index(int row, int col) {
        return row * cols + col;
    }

    private int index(int row, int col, int cs) {
        return  row*cs + col;
    }



    private Pair<Integer, Integer> getRowCol(int index) {
        return new Pair<>(index / cols, index % cols);
    }
}
