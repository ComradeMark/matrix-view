package edu.vanier.matrixView.animations;

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

public class Graphs extends Application {
    //Todo see how we can update the coordinate system while the screen has a new dimension
    int x = 2;
    int y = 2;
    int w = 2;
    int h = 2;
    int initialScreenHeight = 1010;
    int initialScreenWidth = 1010;
    int[] origin = {initialScreenWidth / 2 -2, initialScreenHeight /2 -2};

    // launch the application
    public void start(Stage stage)
    {


        // set title for the stage
        stage.setTitle("creating canvas");

        // create a canvas
        Canvas canvas = new Canvas(initialScreenHeight , initialScreenWidth );

        // graphics context
        GraphicsContext graphics_context =
                canvas.getGraphicsContext2D();


        // create a Group
        Group group = new Group(canvas);

        // create a scene
        Scene scene = new Scene(group, initialScreenWidth, initialScreenHeight);

        // loop for creating points
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                    graphics_context.setFill(Color.BLACK);
                    graphics_context.fillOval(x, y, w, h);
                    x += 50;

            }
            x = 2;
            y += 50;

        }

        //Create axis lines
        Line xAxis = new Line(0, origin[1], initialScreenWidth, origin[1]);
        Line yAxis = new Line(origin[0],0,origin[0],initialScreenHeight);
        Line arrow = new Line(origin[0], origin[1], 512, 531);
        arrow.setStroke(Color.RED);
        arrow.setStrokeWidth(3);
        group.getChildren().addAll(xAxis, yAxis, arrow);

        // set the scene
        stage.setScene(scene);

        stage.show();
    }

    // Main Method
    public static void main(String args[])
    {

        // launch the application
        launch(args);
    }

}