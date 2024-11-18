package edu.vanier.matrixView.math;

import java.util.ArrayList;
import java.util.LinkedList;

import jdk.jshell.spi.ExecutionControl;
 public class Calculator {
     public static ArrayList<String> operations = new ArrayList<>();


     public static ArrayList<Coordinate> matrixMultiply(Matrix matrix, ArrayList<Coordinate> coordinates) {
        ArrayList<Coordinate> outs = new ArrayList<>();
        double a = matrix.getA();
        double b = matrix.getB();
        double c = matrix.getC();
        double d = matrix.getD();


        for (Coordinate inCoord : coordinates) {
            double x = inCoord.getX();
            double y = inCoord.getY();

            if (inCoord.getClass() == Vector.class) {
                outs.add(new Vector(
                        a * x + b * y,
                        c * x + d * y
                ));
                continue;
            }
            outs.add(new Coordinate(
                    a * x + b * y,
                    c * x + d * y
            ));
        }

        return outs;
    }


    public static Vector[] matrixAdd(Matrix matrix, Coordinate[] coordinate) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

    public static double determinant(Matrix matrix) {
        return matrix.getA() * matrix.getD() - matrix.getB() * matrix.getC();
    }

    public static Matrix adjugate(Matrix matrix) {
        double a = matrix.getD();
        double d = matrix.getA();
        double b = matrix.getB() * -1;
        double c = matrix.getC() * -1;
        return new Matrix(a, b, c, d);
    }

    public static Matrix inverse(Matrix matrix) {
        double det = determinant(matrix);
        Matrix temp = Calculator.adjugate(matrix);
        temp.setA(temp.getA() * det);
        temp.setB(temp.getB() * det);
        temp.setC(temp.getC() * det);
        temp.setD(temp.getD() * det);

        return temp;
    }

    public static boolean isInvertible(Matrix matrix) {
        return Calculator.determinant(matrix) != 0;
    }

    /**
     * Method that finds the row echelon form of a 2x2 matrix using the Gaussian algorithm
     */

    public static Matrix rowEchelon(Matrix matrix) {
        //Storage for operations

        //Creates temporary storage row echelon matrix
        Matrix rref = new Matrix(matrix.getA(), matrix.getB(), matrix.getC(), matrix.getD());
        //Creates rows and columns of matrix as arrays for easier manipulation
        double[] row1 = new double[2];
        row1[0] = rref.getA();
        row1[1] = rref.getB();
        double[] row2 = new double[2];
        row2[0] = rref.getC();
        row2[1] = rref.getD();

        //Begin with an m × n matrix A. If A = 0, matrix is already row-reduced
        if (matrix.getA() == 0.00 && matrix.getB() == 0.00 && matrix.getC() == 0.00 && matrix.getD() == 0.00) {
            rref = matrix;
        } else {
            //Determine the leftmost non-zero column
            double[] column1 = new double[2];
            column1[0] = rref.getA();
            column1[1] = rref.getC();
            double[] column2 = new double[2];
            column2[0] = rref.getB();
            column2[1] = rref.getD();
            double[] pivotColumn = new double[2];
            //Check if first element of row 1 is non-zero
            if (row1[0] != 0.00) {
                //Use elementary row operations to put a 1 in the topmost position
                double factor = row1[0];
                row1[0] /= factor;
                row1[1] /= factor;
                operations.add(factor + " * row 1" + " -> Row 1 ");
                System.out.println(factor + " * row 1" + " -> Row 1 ");

            }

            //Use elementary row operations to put zeros strictly below the pivot position
            if (row2[0] != 0) {
                double factor = row2[0];
                row2[0] -= factor * row1[0];
                row2[1] -= factor * row1[1];
                operations.add(factor + " * row 1" + " + Row 2 " + " -> Row 2 ");
                System.out.println(factor + " * row 1" + " + Row 2 " + " -> Row 2 ");

            }
            //Continue with EROs along the main diagonal
            if(row2[1] != 0.00){
                double factor = row2[1];
                row2[1] /= factor;
                operations.add(factor + " * row 2" + " -> Row 2 ");
                System.out.println(factor + " * row 2" + " -> Row 2 ");

            }
            rref.setA(row1[0]);
            rref.setB(row1[1]);
            rref.setC(row2[0]);
            rref.setD(row2[1]);
            printMatrix(rref);

            return rref;
        }
    return rref; }

    /**
     * Static method that multiplies a row of matrix values by a scalar. Declared as a static to be able to use without instantiation.
     */
    private static double[] rowScalar(double pivotValue, double[] row, int rowValue){
        System.out.println("Initial row: " + row.toString());
        double scalar = 1/pivotValue;
        row[0] = scalar*row[0];
        row[1] = scalar*row[1];
        return row;
    }
    /**
     * Static method that swaps two arrays of matrix values. Declared as a static to be able to use without instantiation.
     */
    private static void rowSwap(){

    }
    /**
     * Static method that multiplies a row of matrix values by a scalar, before adding that
     * result to another array. Declared as a static to be able to use without instantiation.
     */
    private static void rowTimesAndAdd(double factor, double[] returnedArray, double[] enteredArray){
        
    }
    private static void printMatrix(Matrix matrix){
        operations.add("[" + matrix.getA() + " " + matrix.getB() + "]");
        operations.add("[" + matrix.getC() + " " + matrix.getD() + "]");
        System.out.println("[" + matrix.getA() + " " + matrix.getB() + "]");
        System.out.println("[" + matrix.getC() + " " + matrix.getD() + "]");
    }

    public static Matrix matrixSubtract(Matrix m1, Matrix m2){
        return new Matrix(
                m1.getA() - m2.getA(),
                m1.getB() - m2.getB(),
                m1.getC() - m2.getC(),
                m1.getD() - m2.getD()
        );
    }
    
    public static Matrix matrixAdd(Matrix m1, Matrix m2){
        return new Matrix(
                m1.getA() + m2.getA(),
                m1.getB() + m2.getB(),
                m1.getC() + m2.getC(),
                m1.getD() + m2.getD()
        );
    }
    
    public static Matrix scalarMult(double scalar, Matrix m){
        return new Matrix(
                m.getA() * scalar,
                m.getB() * scalar,
                m.getC() * scalar,
                m.getD() * scalar
        );
    }
}
