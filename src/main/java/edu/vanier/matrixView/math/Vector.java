package edu.vanier.matrixView.math;

/**
 * Created the initial version of the Vector class (inherits Coordinate)
 *
 * Magnitude is being calculated from the origin (by definition of a vector)
 */


public class Vector extends Coordinate{
    double magnitude;
    public Vector(double x, double y) {
        super(x, y);
        magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) );
    }

    public double getMagnitude() {
        return magnitude;
    }
}
