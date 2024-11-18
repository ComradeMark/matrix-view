package edu.vanier.matrixView.controllers;

import edu.vanier.matrixView.math.Calculator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static edu.vanier.matrixView.controllers.MainAppFXMLController.userMatrix;


public class detInfoController {

    @FXML
    private TextField detField;
    @FXML
    private TextArea outputArea;
    @FXML
    public void initialize(){
        String outputText = "";

        detField.setEditable(false);
        detField.setText(String.valueOf(Calculator.determinant(userMatrix)));
        Calculator.rowEchelon(userMatrix);
        outputArea.clear();
        for (int i = 0; i < Calculator.operations.size(); i++) {
            outputText = outputText + Calculator.operations.get(i) + "\n";
        }
        outputArea.setText(outputText + "\n");
    }

}
