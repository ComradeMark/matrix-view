package edu.vanier.matrixView.math;

/**
 * Created the initial version of the Vector class (inherits Coordinate)
 * Magnitude is being calculated from the origin (by definition of a vector)
 */

public class Vector extends Coordinate {

    double magnitude;

    /**
     * Method that initializes a vector according to an x and y component
     *
     * @param x the horizontal component of the vector
     * @param y the vertical component of the vector
     */
    public Vector(double x, double y) {
        super(x, y);
        magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) );
    }

    @Override
    public String toString() {
        return "{"+super.toString() + ", Vector{" + "magnitude=" + magnitude + "}}";
    }

}
