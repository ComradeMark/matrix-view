package edu.vanier.matrixView.test;

import edu.vanier.matrixView.math.Calculator;
import edu.vanier.matrixView.math.Coordinate;
import edu.vanier.matrixView.math.Matrix;
import edu.vanier.matrixView.math.Vector;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Matrix mtx = new Matrix(1, 0, 0, 1);
        Vector v = new Vector(3, 4);
        
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(v);
        System.out.println(Calculator.matrixMultiply(mtx, coordinates));
        Matrix m1 = new Matrix(2, 3, -2, 4);
        Calculator c1 = new Calculator();
        Calculator.rowEchelon(m1);
    }
}
