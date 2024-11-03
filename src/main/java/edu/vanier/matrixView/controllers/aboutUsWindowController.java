package edu.vanier.matrixView.controllers;

import edu.vanier.matrixView.test.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class aboutUsWindowController {

    @FXML
    private Button btnClose;

    public void initialize(){


    }
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


}
