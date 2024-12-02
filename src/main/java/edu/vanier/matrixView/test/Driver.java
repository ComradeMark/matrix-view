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
        
        System.out.println("Take Matrix: " + mtx + " and Vector " + v);
        // Test matrix-vector multiplication
        System.out.println("Matrix-vector mult: " + Calculator.matrixMultiply(mtx, coordinates));
        
        // Testing scalar-matrix multiplication (used for animation)
        System.out.println("Scalar-matrix mult (2 * Matrix): " + Calculator.scalarMult(2, mtx));

        // Calculate matrix values
        System.out.println("Invertible: " + Calculator.isInvertible(mtx));
        System.out.println("Determinant: " + Calculator.determinant(mtx));
        System.out.println("Determinant: " + Calculator.determinant(mtx));
        
        // Row reduction
        System.out.println("Row echelon reduction: " + Calculator.rowEchelon(mtx));
        
        // Testing matrix-matrix operations
        Matrix mtx2 = new Matrix(1, 1, 1, 1);
        System.out.println("Given a second matrix " + mtx2);
        System.out.println("Matrix + Matrix: " + Calculator.matrixAdd(mtx, mtx2));
        System.out.println("Matrix - Matrix: " + Calculator.matrixSubtract(mtx, mtx2));
        
    }
}
