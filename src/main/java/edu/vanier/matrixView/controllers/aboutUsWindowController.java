package edu.vanier.matrixView.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class  aboutUsWindowController {

    @FXML
    private Button btnClose;

    public void initialize(){
        btnClose.setOnAction(this::handleCloseButtonAction);
    }
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
