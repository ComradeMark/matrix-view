package edu.vanier.matrixView.UI;

import edu.vanier.matrixView.controllers.aboutUsWindowController;
import edu.vanier.matrixView.test.Driver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class aboutUsStage extends Stage {

    public aboutUsStage(){
        initComponents();
        initModality(Modality.APPLICATION_MODAL);

    }
    private void initComponents(){
        Stage aboutUsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Driver.class.getResource("/fxml/aboutUs.fxml"));
        loader.setController(new aboutUsWindowController());
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            aboutUsStage.setScene(scene);
            aboutUsStage.setTitle("About us");
            aboutUsStage.setAlwaysOnTop(true);
            aboutUsStage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}