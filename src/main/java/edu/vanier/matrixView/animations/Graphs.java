package edu.vanier.matrixView.animations;

import javafx.application.Application;
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

    int x = 30;
    int y = 250;
    int w = 10;
    int h = 10;
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


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    graphics_context.setFill(Color.BLUE);
                    graphics_context.fillOval(x, y, w, h);
                    x += 30;

            }
            x = 30;
            y += 30;

        }


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