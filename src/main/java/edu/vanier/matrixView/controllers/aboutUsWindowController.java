package edu.vanier.matrixView.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class  aboutUsWindowController {
    @FXML
    private Button btnClose;

    public void initialize(){
        btnClose.setStyle("-fx-font-size: 12px;" +
                        "-fx-background-color: #3498db;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-radius: 3px;" +
                        "-fx-border-color: #2980b9;" +
                        "-fx-padding: 6px 8px;"
        );
        btnClose.setOnAction(this::handleCloseButtonAction);
    }
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
