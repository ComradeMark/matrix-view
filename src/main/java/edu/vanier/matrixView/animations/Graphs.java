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
    
    public int spacing = 20;

    private Vector ihat;
    private Vector jhat;
    private Matrix tfm;

    public Graphs(Matrix userMatrix) {
        ihat = new Vector(userMatrix.getA(), userMatrix.getB());
        jhat = new Vector(userMatrix.getC(), userMatrix.getD());
        tfm = new Matrix(ihat.getX(), ihat.getY(), jhat.getX(), jhat.getY());
;    }

//Removed all user graphs from pane. Used in reset method.
    public void removeGraph(GraphicsContext gc){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    
    
    public GraphicsContext drawGraph(int width, int height, Canvas canvas, Color graphColor, Color secondaryColor){

        int[] offsets = {width / 2, height /2};
     
        // graphics context
        GraphicsContext gc =
                canvas.getGraphicsContext2D();


//        int x = 0;
//        int y = 0;
        int w = 2;
        int h = 2;

        int numAxisPts = 40;
        gc.setFill(Color.BLACK);
        // loop for creating points
        for (int i = -numAxisPts/2; i < numAxisPts/2 + 1; i++) {
            if(i == 0){continue;}
//            gc.fillOval(i * spacing + offsets[0], offsets[1], w, h);
            xCoords.add(new Coordinate(i * spacing , 0));
        }
        for (int j = -numAxisPts/2; j < numAxisPts/2 + 1; j++) {
            if(j == 0){continue;}
//            gc.fillOval(offsets[0], j * spacing + offsets[1], w, h);
            yCoords.add(new Coordinate(0, j * spacing));

        }


        
        double lineLen = 10000;
        ArrayList<Coordinate> tfmXCoords = Calculator.matrixMultiply(tfm, xCoords);
        ArrayList<Coordinate> tfmYCoords = Calculator.matrixMultiply(tfm, yCoords);
        
        System.out.println(xCoords);
        System.out.println(tfmXCoords);
        System.out.println(yCoords);
        System.out.println(tfmYCoords);
        
        gc.setLineWidth(2);
        gc.setStroke(secondaryColor);
        for (Coordinate coordinate: tfmXCoords){   
            gc.strokeLine(coordinate.getX() + offsets[0] - lineLen * jhat.getX(), 
                    coordinate.getY() + offsets[1] + lineLen * jhat.getY(),
                    coordinate.getX() + offsets[0] + lineLen * jhat.getX(), 
                    coordinate.getX() + offsets[1] - lineLen * jhat.getY());

        }

        for (Coordinate coordinate: tfmYCoords){   
            gc.strokeLine(coordinate.getX() + offsets[0] - lineLen * ihat.getX(), 
                    coordinate.getY() + offsets[1] + lineLen * ihat.getY(),
                    coordinate.getX() + offsets[0] + lineLen * ihat.getX(), 
                    coordinate.getY() + offsets[1] - lineLen * ihat.getY());
        }
        
        // Drawn main axes
        gc.setStroke(graphColor);
        
        gc.strokeLine(offsets[0]- lineLen *ihat.getX(), 
                offsets[1] + lineLen * ihat.getY(), 
                offsets[0] + lineLen *ihat.getX(), 
                offsets[1] - lineLen * ihat.getY());
        gc.strokeLine(offsets[0]- lineLen *jhat.getX(), 
                offsets[1] + lineLen * jhat.getY(), 
                offsets[0] + lineLen *jhat.getX(), 
                offsets[1] - lineLen * jhat.getY());
    return gc;

    }
    public Line drawLine() {
        Line line = new Line();
        return  line;
    }

    public void drawDefaultSpace(int width, int height, Canvas canvasPane){
        Matrix simpleBasis = new Matrix(1, 0, 0, 1);
        Graphs mainGraph = new Graphs(simpleBasis);
        mainGraph.drawGraph(width, height, canvasPane, Color.BLACK, Color.LIGHTGRAY);
    }
}