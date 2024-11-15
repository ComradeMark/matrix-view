package edu.vanier.matrixView.test;

import edu.vanier.matrixView.controllers.MainAppFXMLController;
import edu.vanier.matrixView.math.Calculator;
import edu.vanier.matrixView.math.Coordinate;
import edu.vanier.matrixView.math.Matrix;
import edu.vanier.matrixView.math.Vector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.animation.Timeline;

public class Driver extends Application {
    public static void main(String[] args) {
        Matrix mtx = new Matrix(1, 0, 0, 1);
        Vector v = new Vector(3, 4);
        
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(v);
        System.out.println(Calculator.matrixMultiply(mtx, coordinates));
        Matrix m1 = new Matrix(2, 3, -2, 4);
        Calculator c1 = new Calculator();
        Calculator.rowEchelon(m1);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Driver.class.getResource("/fxml/MainAppMinimalViablePage.fxml"));
        loader.setController(new MainAppFXMLController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MatrixView Home");
        primaryStage.show();
        


    }
    
    public void updateCanvas(){
        
    }
}
