package edu.vanier.matrixView.controllers;

import edu.vanier.matrixView.math.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static edu.vanier.matrixView.controllers.MainAppFXMLController.userMatrix;

/**
 * Class that acts as a controller for the determinant information page.
 */
public class detInfoController {

    @FXML
    private TextField detField;
    @FXML
    private TextArea outputArea;
    @FXML
    private Button goBack;

    /**
     * Method that initializes the determinant information page.
     * It also styles the exit button.
     */
    public void initialize(){
        goBack.setStyle("-fx-font-size: 12px;" +
                "-fx-background-color: #3498db;" +
                "-fx-text-fill: white;" +
                "-fx-border-radius: 3px;" +
                "-fx-border-color: #2980b9;" +
                "-fx-padding: 6px 8px;"
        );
        outputArea.setStyle("-fx-border-color: #3498db;" +
                            "-fx-border-radius: 3px;" +
                            "-fx-padding: 4px;"
        );

        String outputText = "";

        detField.setEditable(false);
        detField.setText(String.valueOf(Calculator.determinant(userMatrix)));
        Calculator.rowEchelon(userMatrix);
        outputArea.clear();
        for (int i = 0; i < Calculator.operations.size(); i++) {
            outputText = outputText + Calculator.operations.get(i) + "\n";
        }
        outputArea.setText(outputText + "\n");

        goBack.setOnAction(this::handleGoBack);
    }

    /**
     * Method that allows the determinant stage to be closed
     * @param event the expected user event
     */
    public void handleGoBack(ActionEvent event) {
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.close();
    }

}
