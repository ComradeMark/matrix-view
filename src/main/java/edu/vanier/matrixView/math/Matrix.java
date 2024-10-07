package edu.vanier.matrixView.math;

/**
 * Custom matrix class that will allow us to work with matrices.
 * Letters a,b,c, and d represents the entries of a 2 x 2 matrix
 * The order of the entries are such:

 A(11) = a
 A(12) = b
 A(21) = c
 A(22) = d

 */


public class Matrix {
    public double a;
    public double b;
    public double c;
    public double d;


    public Matrix(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }

    public static double determinant(Matrix matrix) {
        return matrix.getA()*matrix.getD() - matrix.getB()*matrix.getC();
    }

    public static Matrix adjugate(Matrix matrix) {
        matrix.setA(matrix.getD());
        matrix.setD(matrix.getA());
        matrix.setB(matrix.getB()*-1);
        matrix.setC(matrix.getC()*-1);
        return matrix;
    }

    public static Matrix inverse(Matrix matrix) {
        double det = determinant(matrix);
        Matrix temp = Matrix.adjugate(matrix);
        temp.setA(temp.getA() * det);
        temp.setB(temp.getB() * det);
        temp.setC(temp.getC() * det);
        temp.setD(temp.getD() * det);

        return temp;
    }


}



