package edu.vanier.matrixView.math;

/**
 * Custom class that creates a coordinate
 * Letters x and y represent the entries of the coordinate
 * The order of the entries are such: P(x, y)
 */

public class Coordinate {
    public double x;
    public double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
