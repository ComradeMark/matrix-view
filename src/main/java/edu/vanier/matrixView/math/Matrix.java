package edu.vanier.matrixView.math;

public class Matrix {
    public double x;
    public double y;

    public Matrix(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return ("(" + x + "," + y + ")");
    }
}
