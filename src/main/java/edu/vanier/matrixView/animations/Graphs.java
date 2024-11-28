package edu.vanier.matrixView.animations;

import edu.vanier.matrixView.math.Calculator;
import edu.vanier.matrixView.math.Coordinate;
import edu.vanier.matrixView.math.Matrix;
import edu.vanier.matrixView.math.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Class that contains the major logic of drawing graphs.
 */
public class Graphs {

    private ArrayList<Coordinate> xCoords = new ArrayList<>();
    private ArrayList<Coordinate> yCoords = new ArrayList<>();
    private Coordinate origin = new Coordinate(0, 0);
    int spacing = 30;
    private Vector ihat;
    private Vector jhat;
    private Matrix tfm;
    private double coordW = 10;
    private double coordH = 10;


    /**
     * Constructor for the Graph object.
     *
     * @param userMatrix the entered matrix by the user
     */
    public Graphs(Matrix userMatrix) {
        ihat = new Vector(userMatrix.getA(), userMatrix.getC());
        jhat = new Vector(userMatrix.getB(), userMatrix.getD());
        tfm = userMatrix;
    }


    /**
     * Method that removes all user graphs from pane. Used in reset method.
     *
     * @param gc The entered GraphicsContext object
     */
    public void removeGraph(GraphicsContext gc){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    /**
     * Method that draws a graph on a canvas using specified dimensions, colors, and spacing.
     *
     * @param width The width of the canvas
     * @param height The height of the canvas
     * @param canvas The canvas object to draw the graph on.
     * @param graphColor The color of the main axes
     * @param secondaryColor The color of the grid lines
     * @param spacing The spacing between grid lines
     * @return The GraphicsContext used for rendering the graph
     */
    public GraphicsContext drawGraph(double width, double height, Canvas canvas, Color graphColor, Color secondaryColor, double spacing){

        double[] offsets = {width / 2, height /2};

        // graphics context
        GraphicsContext gc =
                canvas.getGraphicsContext2D();
        int numAxisPts = 1000;
        gc.setFill(Color.BLACK);
        // loop for creating points
        for (int i = -numAxisPts/2; i < numAxisPts/2 + 1; i++) {
            if(i == 0){continue;}
            xCoords.add(new Coordinate(i * spacing , 0));
        }
        for (int j = -numAxisPts/2; j < numAxisPts/2 + 1; j++) {
            if(j == 0){continue;}
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
        gc.setGlobalAlpha(1);
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

    /**
     *  Method that draws gridlines and new bases. Takes list of items to draw on canvas, and draws
     *  them while maintaining linearity. Uses spacing and canvas dimensions for operations.
     *
     * @param initItems
     * @param canvas
     *
     */
    public void drawItems(ArrayList<Coordinate> initItems, Canvas canvas){
        double[] offsets = {canvas.getWidth() / 2, canvas.getHeight() /2};
        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        gc.setStroke(Color.BLUE);
        ArrayList<Coordinate> tfmItems = Calculator.matrixMultiply(tfm, initItems);

        for (Coordinate item: tfmItems){
            if (item.getClass() == Vector.class){
                gc.strokeLine(offsets[0], offsets[1], item.getX() + offsets[0],
                        -item.getY()+ offsets[1]);
                continue;
            }
            gc.fillOval(item.getX() + offsets[0] - coordW/2,
                    -item.getY() + offsets[1] - coordH/2, coordW, coordH);
        }
    }

    /**
     * Method that draws the main gridlines
     * @param width The width of the canvas
     * @param height The height of the canvas
     * @param canvasPane The canvas object where the main axes will be drawn
     */
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
