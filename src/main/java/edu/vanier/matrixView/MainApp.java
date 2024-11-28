package edu.vanier.matrixView;

import edu.vanier.matrixView.controllers.MainAppFXMLController;
import edu.vanier.matrixView.test.Driver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class MainApp extends Application {

    public static final String MAINAPP_LAYOUT = "MainAppMinimalViablePage";
    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            FXMLLoader loader = new FXMLLoader(Driver.class.getResource("/fxml/MainAppMinimalViablePage.fxml"));
            loader.setController(new MainAppFXMLController());
            Parent root = loader.load();
            scene = new Scene(root);

            scene.getStylesheets().add("/css/MainPage.css");

            primaryStage.setScene(scene);
            primaryStage.setTitle("MatrixView Home");
            primaryStage.show();
            primaryStage.sizeToScene();

            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
    }
    

    /**
     * Loads a scene graph from an FXML file.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     * @return The root node of the loaded scene graph.
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
