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
    public static final String MAINAPP_LAYOUT = "MainAppMinimalViablePage";

    int x = 0;
    int y = 0;
    int w = 2;
    int h = 2;
    // launch the application
    public void start(Stage stage)
    {


        // set title for the stage
        stage.setTitle("creating canvas");

        // create a canvas
        Canvas canvas = new Canvas(1000, 1000);

        // graphics context
        GraphicsContext graphics_context =
                canvas.getGraphicsContext2D();


        // create a Group
        Group group = new Group(canvas);

        // create a scene
        Scene scene = new Scene(group, 1000, 1000);


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
        Line xAxis = new Line(0, 500, 1000, 500);
        group.getChildren().add(xAxis);



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