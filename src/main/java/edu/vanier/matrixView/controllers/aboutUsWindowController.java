package edu.vanier.matrixView.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * Class that acts as a controller for about us page.
 */
public class  aboutUsWindowController {

    @FXML
    private Button btnClose;

    /**
     * Method that initializes the determinant information page.
     * It also styles the exit button.
     * */
    public void initialize(){
        btnClose.setStyle("-fx-font-size: 12px;" +
                        "-fx-background-color: #C8C8C8;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-radius: 3px;" +
                        "-fx-border-color: #9E9E9E;" +
                        "-fx-padding: 6px 8px;"
        );
        btnClose.setOnAction(this::handleCloseButtonAction);
    }

    /**
     * Method that allows the determinant stage to be closed
     * @param event the expected user event
     */
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
