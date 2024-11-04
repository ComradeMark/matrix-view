package edu.vanier.matrixView.animations;

import edu.vanier.matrixView.math.Coordinate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;

public class Graphs {
    //Todo see how we can update the coordinate system while the screen has a new dimension
//    private int x = -1;
//    private int y = -1;
//    private int w = 2;
//    private int h = 2;
    private int initialScreenHeight = 300;
    private int initialScreenWidth = 600;
    private int[] origin = {initialScreenWidth / 2 - 4, initialScreenHeight /2 - 4};
    
    private ArrayList<Coordinate> coordinates = new ArrayList<>();

//    public Graphs() {
//        for (int i = 0; i < 100; i++) {
//            for (int j = 0; j < 100; j++) {
//                    coordinates.add(new Coordinate(x, y));
//                    x += 50;
//                    
//            }
//            x = -1;
//            y += 50;
//        }
//    }
    
    

//    // launch the application
//    public void start(Stage stage)
//    {
//
//
//        // set title for the stage
//        stage.setTitle("creating canvas");
//
//        // create a canvas
//        Canvas canvas = new Canvas(initialScreenWidth , initialScreenHeight );
//
//        // graphics context
//        GraphicsContext graphics_context =
//                canvas.getGraphicsContext2D();
//
//
//        // create a Group
//        Group group = new Group(canvas);
//        
//        int spacing = 10;
//
//        // create a scene
//        Scene scene = new Scene(group, initialScreenWidth, initialScreenHeight);
//
//        graphics_context.setFill(Color.BLACK);
//        // loop for creating points
//        for (int i = 0; i < 100; i++) {
//            for (int j = 0; j < 100; j++) {
//                    graphics_context.fillOval(x, y, w, h);
//                    coordinates.add(new Coordinate(x, y));
//                    x += spacing;
//                    
//            }
//            x = -1;
//            y += spacing;
//        }
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setStroke(Color.LIGHTGRAY);
//        gc.setLineWidth(0.5);
//        // adds vertical lines
//        for (int x = 0; x < canvas.getWidth(); x += spacing) {
//            if (Math.abs(origin[0] - x) <= spacing/2){
//                gc.setStroke(Color.BLACK);
//                gc.strokeLine(x, 0, x, canvas.getHeight());
//                gc.setStroke(Color.LIGHTGRAY);
//                continue;
//            }
//            gc.strokeLine(x, 0, x, canvas.getHeight());
//        }
//        // adds horizontal lines
//        for (int y = 0; y < canvas.getHeight(); y += spacing) {
//            if (Math.abs(origin[1] - y) <= spacing/2){
//                gc.setStroke(Color.BLACK);
//                gc.strokeLine(0, y, canvas.getWidth(), y);
//                gc.setStroke(Color.LIGHTGRAY);
//                continue;
//            }
//            gc.strokeLine(0, y, canvas.getWidth(), y);
//        }
//        
//        Line arrow = new Line(origin[0], origin[1], 512, 531);
//        arrow.setStroke(Color.RED);
//        arrow.setStrokeWidth(3);
////        group.getChildren().addAll(xAxis, yAxis, arrow);
//
//        // set the scene
//        stage.setScene(scene);
//
//        stage.show();
//    }

//    // Main Method
//    public static void main(String args[])
//    {
//
//        // launch the application
//        launch(args);
//    }
    

    public Canvas getGraph(int width, int height){
        Canvas canvas = new Canvas(width , height);

        int[] origin = {width / 2 - 4, height /2 - 4};
        // graphics context
        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        int spacing = 10;

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

        return canvas;
    }
}