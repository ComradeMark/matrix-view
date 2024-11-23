package edu.vanier.matrixView.math;

import java.util.ArrayList;

 public class Calculator {

     public static ArrayList<String> operations = new ArrayList<>();

     /**
      * Method that multiplies a given matrix by a given coordinate
      *
      * @param matrix      the inputted two by two matrix
      * @param coordinates the inputted coordinate
      * @return the resultant multiplication of the two entries
      */
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

     /**
      * Method that calculates the determinant of a matrix
      *
      * @param matrix the inputted two by two matrix
      * @return returns the determinant of the matrix
      */
     public static double determinant(Matrix matrix) {
         return matrix.getA() * matrix.getD() - matrix.getB() * matrix.getC();
    }

     /**
      * Method that calculates the adjugate of a matrix
      *
      * @param matrix the inputted two by two matrix
      * @return cofactor matrix of the entered matrix
      */
     public static Matrix adjugate(Matrix matrix) {
         double a = matrix.getD();
        double d = matrix.getA();
        double b = matrix.getB() * -1;
        double c = matrix.getC() * -1;
        return new Matrix(a, b, c, d);
    }

     /**
      * Method that returns the inverse of a matrix
      *
      * @param matrix the inputted two by two matrix
      * @return the inverse of the inputted matrix
      */
     public static Matrix inverse(Matrix matrix) {
        double det = determinant(matrix);
        Matrix temp = Calculator.adjugate(matrix);
        temp.setA(temp.getA() * det);
        temp.setB(temp.getB() * det);
        temp.setC(temp.getC() * det);
        temp.setD(temp.getD() * det);
        return temp;
    }

     /**
      * Method that determines if a matrix is invertible or not
      * @param matrix the inputted two by two matrix
      * @return the boolean value whether the matrix is invertible or not
      */
     public static boolean isInvertible(Matrix matrix) {
        return Calculator.determinant(matrix) != 0;
     }

     /**
      * Method that allows to use the row reduction process for two by two matrices
      * @param matrix the inputted two by two matrix
      * @return the simplified two by two matrix
      */
     public static Matrix rowEchelon(Matrix matrix) {
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
            //Check if the first element of row 1 is non-zero
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
        return rref;
     }

     /**
      * Method that prints a matrix (for debugging purposes)
      * @param matrix the inputted two by two matrix
      */
     private static void printMatrix(Matrix matrix){
        operations.add("[" + matrix.getA() + " " + matrix.getB() + "]");
        operations.add("[" + matrix.getC() + " " + matrix.getD() + "]");
        System.out.println("[" + matrix.getA() + " " + matrix.getB() + "]");
        System.out.println("[" + matrix.getC() + " " + matrix.getD() + "]");
    }

     /**
      * Method that allows one matrix to be subtracted from another
      * @param m1 the first inputted two by two matrix
      * @param m2 the second inputted two by two matrix
      * @return the resultant two by two matrix
      */
     public static Matrix matrixSubtract(Matrix m1, Matrix m2){
        return new Matrix(
                m1.getA() - m2.getA(),
                m1.getB() - m2.getB(),
                m1.getC() - m2.getC(),
                m1.getD() - m2.getD()
        );
    }

     /**
      * Method that adds a matrix to another matrix
      * @param m1 the first inputted two by two matrix
      * @param m2 the second inputted two by two matrix
      * @return the resultant two by two matrix
      */
     public static Matrix matrixAdd(Matrix m1, Matrix m2){
        return new Matrix(
                m1.getA() + m2.getA(),
                m1.getB() + m2.getB(),
                m1.getC() + m2.getC(),
                m1.getD() + m2.getD()
        );
    }

     /**
      * Method that allows a matrix multiplication with a scalar value
      * @param scalar the entered multiplier value
      * @param m  the inputted two by two matrix
      * @return the resultant two by two matrix
      */
     public static Matrix scalarMult(double scalar, Matrix m){
        return new Matrix(
                m.getA() * scalar,
                m.getB() * scalar,
                m.getC() * scalar,
                m.getD() * scalar
        );
    }

 }
