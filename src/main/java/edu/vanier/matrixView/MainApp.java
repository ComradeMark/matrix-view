package edu.vanier.matrixView;

import edu.vanier.matrixView.animations.Graphs;
import edu.vanier.matrixView.controllers.MainAppFXMLController;
import java.io.IOException;
import java.util.logging.Level;

import edu.vanier.matrixView.math.Calculator;
import edu.vanier.matrixView.math.Matrix;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainApp extends Application {

    public static final String MAINAPP_LAYOUT = "MainAppMinimalViablePage";
    public static final String SECONDARY_LAYOUT = "secondary_layout";
    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) {

        try {


            logger.info("Bootstrapping the application...");
            // Load the scene of the primary stage.
            MainAppFXMLController controller = new MainAppFXMLController();
            BorderPane root = (BorderPane)loadFXML(MAINAPP_LAYOUT, controller);
            Graphs graph = new Graphs();
            controller.setCanvasPane(graph.getGraph(400, 400));
            System.out.println(((Canvas)((Pane)root.getChildren().get(1)).getChildren().get(0)).getTranslateX());
            scene = new Scene(root, 640, 480);
            primaryStage.setScene(scene);

            primaryStage.sizeToScene();
            // Put this appliation's main window on top of other already-opened windows
            // upon launching the app.
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Changes the primary stage's current scene.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     */
    public static void switchScene(String fxmlFile, Object fxmlController) {
        try {
            scene.setRoot(loadFXML(fxmlFile, fxmlController));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads a scene graph from an FXML file.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     * @return The root node of the loaded scene graph.
     * @throws IOException
     */
    public static Parent loadFXML(String fxmlFile, Object fxmlController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxmlFile + ".fxml"));
        fxmlLoader.setController(fxmlController);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {





        launch(args);
    }

}
