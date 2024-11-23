package edu.vanier.matrixView.math;

/**
 * Custom class that creates a coordinate
 * Letters x and y represent the entries of the coordinate
 */

public class Coordinate {
    public double x;
    public double y;

    /**
     * Constructor of a coordinate object that takes two entries.
     * The order of the entries are such: P(x, y)
     *
     * @param x the entry corresponding to the first value
     * @param y the entry corresponding to the first value
     */
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

    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + '}';
    }

}
