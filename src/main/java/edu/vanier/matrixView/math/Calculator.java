package edu.vanier.matrixView.math;

import java.util.ArrayList;
import jdk.jshell.spi.ExecutionControl;
public class Calculator  {


//    public static Vector Multiply(Matrix matrix, Coordinate coordinate) throws ExecutionControl.NotImplementedException {
//
//        throw new ExecutionControl.NotImplementedException("Not implemented");
//    }

    public static ArrayList<Coordinate> matrixMultiply(Matrix matrix, ArrayList<Coordinate> coordinates){
        ArrayList<Coordinate> outs = new ArrayList<>();
        double a = matrix.getA();
        double b = matrix.getB();
        double c = matrix.getC();
        double d = matrix.getD();


        for (Coordinate inCoord : coordinates) {
            double x = inCoord.getX();
            double y = inCoord.getY();
            
            if(inCoord.getClass() == Vector.class){
                outs.add(new Vector(
                        a * x + b * y,
                        c * x + d * y
                ));
                continue;
            }
            outs.add(new Coordinate(
                    a * x + b * y,
                    c * x + b * y
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
}
