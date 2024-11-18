package edu.vanier.matrixView.UI;

import edu.vanier.matrixView.controllers.aboutUsWindowController;
import edu.vanier.matrixView.controllers.detInfoController;
import edu.vanier.matrixView.test.Driver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class detInfoStage extends Stage {
    public detInfoStage(){
        initComponents();
        initModality(Modality.APPLICATION_MODAL);

    }
    private void initComponents(){
        Stage detInfoStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Driver.class.getResource("/fxml/detInfo.fxml"));
        loader.setController(new detInfoController());
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            detInfoStage.setScene(scene);
            detInfoStage.setTitle("DetInfo Window");
            detInfoStage.setAlwaysOnTop(true);
            detInfoStage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}

