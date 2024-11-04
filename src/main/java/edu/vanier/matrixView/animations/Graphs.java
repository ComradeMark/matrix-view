package edu.vanier.matrixView.animations;

import edu.vanier.matrixView.math.Calculator;
import edu.vanier.matrixView.math.Coordinate;
import edu.vanier.matrixView.math.Matrix;
import edu.vanier.matrixView.math.Vector;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.shape.Line;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class Graphs {
    //Todo see how we can update the coordinate system while the screen has a new dimension

//    private int initialScreenHeight = 300;
//    private int initialScreenWidth = 600;
//    private int[] origin = {initialScreenWidth / 2 - 4, initialScreenHeight /2 - 4};
    
    
    
    private ArrayList<Coordinate> xCoords = new ArrayList<>();
    private ArrayList<Coordinate> yCoords = new ArrayList<>();
    
    
    private Coordinate origin = new Vector(0, 0);

    private Vector ihat;
    private Vector jhat;
    private Matrix tfm;

    public Graphs() {
        ihat = new Vector(0,-1);
        jhat = new Vector(-1,0);
        tfm = new Matrix(ihat.getX(), ihat.getY(), jhat.getX(), jhat.getY());
;    }
    
    
    public void drawGraph(int width, int height, Canvas canvas){

        int[] offsets = {width / 2, height /2};
     
        // graphics context
        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        int spacing = 20;

        int x = 0;
        int y = 0;
        int w = 2;
        int h = 2;
        gc.setFill(Color.BLACK);
        // loop for creating points
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (Math.abs(offsets[0] - x) <= spacing/2 && Math.abs(offsets[1] - y) <= spacing/2){
                    // skip the origin point
                    gc.fillOval(x, y, w, h);

                    x += spacing;
                    continue;
                }
                else if (Math.abs(offsets[0] - x) <= spacing/2){
                    gc.fillOval(x, y, w, h);
                    yCoords.add(new Coordinate(x - offsets[0], y - offsets[1]));
                }   
                else if (Math.abs(offsets[1] - y) <= spacing/2){
                    gc.fillOval(x, y, w, h);
                    xCoords.add(new Coordinate(x - offsets[0], y - offsets[1]));
                }
                x += spacing;

            }
//            x = -1;
            y += spacing;
        }
        
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(5);
        double lineLen = 800;
        // Drawn main axes
        gc.setStroke(Color.BLACK);
        
        gc.strokeLine(offsets[0]- lineLen *ihat.getX(), offsets[1] + lineLen * ihat.getY(), offsets[0] + lineLen *ihat.getX(), offsets[1] - lineLen * ihat.getY());
        gc.strokeLine(offsets[0]- lineLen *jhat.getX(), offsets[1] + lineLen * jhat.getY(), offsets[0] + lineLen *jhat.getX(), offsets[1] - lineLen * jhat.getY());

        System.out.println(origin);

        System.out.println(yCoords);
        System.out.println(xCoords);
        
        ArrayList<Coordinate> tfmXCoords = Calculator.matrixMultiply(tfm, xCoords);
        ArrayList<Coordinate> tfmYCoords = Calculator.matrixMultiply(tfm, yCoords);
        gc.setLineWidth(2);
        gc.setStroke(Color.LIGHTGRAY);
        for (Coordinate coordinate: tfmXCoords){   
            gc.strokeLine(coordinate.getX()- lineLen *jhat.getX(), coordinate.getY() + lineLen * jhat.getY(), coordinate.getX() + lineLen *jhat.getX(), coordinate.getX() - lineLen * jhat.getY());

        }
        for (Coordinate coordinate: tfmYCoords){
            
            gc.strokeLine(coordinate.getX()- lineLen *ihat.getX(), coordinate.getY() + lineLen * ihat.getY(), coordinate.getX() + lineLen *ihat.getX(), coordinate.getX() - lineLen * ihat.getY());

        }
        
        System.out.println(Arrays.toString(offsets));
        System.out.println(offsets[0] + " " + offsets[1] + " " + (offsets[0] + ihat.getX()) + " " + (offsets[1] - ihat.getY()));
        // adds vertical lines
//        for (int x_pos = 0; x_pos < canvas.getWidth(); x_pos += spacing) {
//            if (Math.abs(origin[0] - x_pos) <= spacing/2){
//                gc.setStroke(Color.BLACK);
//                gc.strokeLine(x_pos, 0, x_pos, canvas.getHeight());
//                gc.setStroke(Color.LIGHTGRAY);
//                continue;
//            }
//            gc.strokeLine(x_pos, 0, x_pos, canvas.getHeight());
//        }
//        // adds horizontal lines
//        for (int y_pos = 0; y_pos < canvas.getHeight(); y_pos += spacing) {
//            if (Math.abs(origin[1] - y_pos) <= spacing/2){
//                gc.setStroke(Color.BLACK);
//                gc.strokeLine(0, y_pos, canvas.getWidth(), y_pos);
//                gc.setStroke(Color.LIGHTGRAY);
//                continue;
//            }
//            gc.strokeLine(0, y_pos, canvas.getWidth(), y_pos);
//        }

    }
    public Line drawLine() {
        Line line = new Line();
        return  line;
    }
}