package edu.vanier.matrixView.math;

/**
 * Custom matrix class that will allow us to work with matrices.
 * Letters a,b,c, and d represent the entries of a two times two matrix
 * The order of the entries are such:
 * <p>
 * A(11) = a
 * A(12) = b
 * A(21) = c
 * A(22) = d
 */

public class Matrix {

    public double a;
    public double b;
    public double c;
    public double d;

    /**
     * Constructor that initializes a Matrix given four entries
     *
     * @param a the entry corresponding to the first row and the first column
     * @param b the entry corresponding to the first row and the second column
     * @param c the entry corresponding to the second row and the first column
     * @param d the entry corresponding to the second row and the second column
     */
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

}
