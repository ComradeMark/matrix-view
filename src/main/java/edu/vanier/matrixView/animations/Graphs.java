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


    // Axis grid points
    private ArrayList<Coordinate> xCoords = new ArrayList<>();
    private ArrayList<Coordinate> yCoords = new ArrayList<>();
    private Coordinate origin = new Coordinate(0, 0);
    int spacing = 30;
    private Vector ihat;
    private Vector jhat;
    private Matrix tfm;
    private double coordW = 10;
    private double coordH = 10;

    private ArrayList<Integer> redColor = new ArrayList<>(Arrays.asList(250, 0, 0));
    private ArrayList<Integer> greyColor = new ArrayList<>(Arrays.asList(250, 0, 0));


    public Graphs(Matrix userMatrix) {
        ihat = new Vector(userMatrix.getA(), userMatrix.getC());
        jhat = new Vector(userMatrix.getB(), userMatrix.getD());
        tfm = userMatrix;
    }

//Removed all user graphs from pane. Used in reset method.
    public void removeGraph(GraphicsContext gc){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public GraphicsContext drawGraph(int width, int height, Canvas canvas, Color graphColor, Color secondaryColor, double spacing){

        int[] offsets = {width / 2, height /2};

        // graphics context
        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        int numAxisPts = 1000;
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


        double lineLen = 1000;
        ArrayList<Coordinate> tfmXCoords = Calculator.matrixMultiply(tfm, xCoords);
        ArrayList<Coordinate> tfmYCoords = Calculator.matrixMultiply(tfm, yCoords);


        double w = 5;
        double h = 5;

        gc.setLineWidth(2);
        gc.setStroke(secondaryColor);
        for (Coordinate coordinate: tfmXCoords){
            gc.strokeLine(-coordinate.getX() + offsets[0] - lineLen * jhat.getX(),
                    coordinate.getY() + offsets[1] + lineLen * jhat.getY(),
                    -coordinate.getX() + offsets[0] + lineLen * jhat.getX(),
                    coordinate.getY() + offsets[1] - lineLen * jhat.getY());

            gc.setFill(Color.GRAY);
            gc.fillOval(-coordinate.getX() + offsets[0] - w/2, coordinate.getY() + offsets[1] - h/2, w, h);
        }

        for (Coordinate coordinate: tfmYCoords){
            gc.strokeLine(coordinate.getX() + offsets[0] - lineLen * ihat.getX(),
                    -coordinate.getY() + offsets[1] + lineLen * ihat.getY(),
                    coordinate.getX() + offsets[0] + lineLen * ihat.getX(),
                    -coordinate.getY() + offsets[1] - lineLen * ihat.getY());

            gc.setFill(Color.GRAY);
            gc.fillOval(coordinate.getX() + offsets[0] - w/2, -coordinate.getY() + offsets[1] - h/2, w, h);
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

    public void drawShit(ArrayList<Coordinate> initShit, Canvas canvas){
        double[] offsets = {canvas.getWidth() / 2, canvas.getHeight() /2};
        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        gc.setStroke(Color.BLUE);
        ArrayList<Coordinate> tfmShit = Calculator.matrixMultiply(tfm, initShit);

        for (Coordinate shit: tfmShit){
            if (shit.getClass() == Vector.class){
                gc.strokeLine(offsets[0], offsets[1], shit.getX() + offsets[0],
                        -shit.getY()+ offsets[1]);
                continue;
            }
            gc.fillOval(shit.getX() + offsets[0] - coordW/2,
                    -shit.getY() + offsets[1] - coordH/2, coordW, coordH);
        }
    }

    private Color getColorFromList(ArrayList<Integer> list) {
        // Ensure the list has at least 3 values for RGB
        if (list.size() < 3) {
            throw new IllegalArgumentException("Color list must contain at least 3 values (red, green, blue).");
        }

        // Get the red, green, and blue values
        int red = list.get(0);
        int green = list.get(1);
        int blue = list.get(2);

        // Create and return the Color
        return Color.rgb(red, green, blue);
    }


    public void updateRed() {
        redColor.set(0, redColor.get(0) - 50);
        // Todo might cause a problem if we try to stack more than 5
    }

    public void updateGrey() {
        greyColor.set(0, greyColor.get(0) - 25);
        greyColor.set(1, greyColor.get(1) - 25);
        greyColor.set(2, greyColor.get(2) - 25);
    }

    public void drawDefaultSpace(int width, int height, Canvas canvasPane){
        Matrix simpleBasis = new Matrix(1, 0, 0, 1);
        Graphs mainGraph = new Graphs(simpleBasis);
        mainGraph.drawGraph(width, height, canvasPane, Color.BLACK, Color.LIGHTGRAY, 20
        );
    }

    public ArrayList<Coordinate> getxCoords() {
        return xCoords;
    }

    public void setxCoords(ArrayList<Coordinate> xCoords) {
        this.xCoords = xCoords;
    }

    public ArrayList<Coordinate> getyCoords() {
        return yCoords;
    }

    public void setyCoords(ArrayList<Coordinate> yCoords) {
        this.yCoords = yCoords;
    }

    public Coordinate getOrigin() {
        return origin;
    }

    public void setOrigin(Coordinate origin) {
        this.origin = origin;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public Vector getIhat() {
        return ihat;
    }

    public void setIhat(Vector ihat) {
        this.ihat = ihat;
    }

    public Vector getJhat() {
        return jhat;
    }

    public void setJhat(Vector jhat) {
        this.jhat = jhat;
    }

    public Matrix getTfm() {
        return tfm;
    }

    public void setTfm(Matrix tfm) {
        this.tfm = tfm;
    }

    public double getCoordW() {
        return coordW;
    }

    public void setCoordW(double coordW) {
        this.coordW = coordW;
    }

    public double getCoordH() {
        return coordH;
    }

    public void setCoordH(double coordH) {
        this.coordH = coordH;
    }
}
