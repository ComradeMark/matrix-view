package edu.vanier.matrixView.animations;

import edu.vanier.matrixView.math.Coordinate;
import java.util.ArrayList;

import javafx.scene.shape.Line;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class Graphs {

    //Todo see how we can update the coordinate system while the screen has a new dimension

    public int spacing = 20;

    private int initialScreenHeight = 400;
    private int initialScreenWidth = 400;
    private int[] origin = {initialScreenWidth / 2, initialScreenHeight /2 };
    
    private ArrayList<Coordinate> coordinates = new ArrayList<>();

    public void drawGraph(int width, int height, Canvas canvas){

        int[] origin = {width / 2 , height /2 };
        // graphics context
        GraphicsContext gc =
                canvas.getGraphicsContext2D();


        int x = -1;
        int y = -1;
        int w = 2;
        int h = 2;
        gc.setFill(Color.BLACK);
        // loop for creating points
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                gc.fillOval(x, y, w, h);
                coordinates.add(new Coordinate(x, y));
                x += spacing;

            }
            x = -1;
            y += spacing;
        }
        
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(0.5);
        // adds vertical lines
        for (int x_pos = 0; x_pos < canvas.getWidth(); x_pos += spacing) {
            if (Math.abs(origin[0] - x_pos) <= spacing/2){
                gc.setStroke(Color.BLACK);
                gc.strokeLine(x_pos, 0, x_pos, canvas.getHeight());
                gc.setStroke(Color.LIGHTGRAY);
                continue;
            }
            gc.strokeLine(x_pos, 0, x_pos, canvas.getHeight());
        }
        // adds horizontal lines
        for (int y_pos = 0; y_pos < canvas.getHeight(); y_pos += spacing) {
            if (Math.abs(origin[1] - y_pos) <= spacing/2){
                gc.setStroke(Color.BLACK);
                gc.strokeLine(0, y_pos, canvas.getWidth(), y_pos);
                gc.setStroke(Color.LIGHTGRAY);
                continue;
            }
            gc.strokeLine(0, y_pos, canvas.getWidth(), y_pos);

        }

    }

    public void drawLine(float xEnd, float yEnd,  Canvas canvas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(2.0);

        gc.strokeLine(origin[0],origin[1], origin[0] + xEnd, origin[1]+ yEnd);

            gc.stroke();



    }
}