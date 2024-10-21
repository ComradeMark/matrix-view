package edu.vanier.matrixView.controllers;

import edu.vanier.matrixView.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML controller for controlling the main window.
 *
 */
public class MainAppFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);

    @FXML
    TitledPane configPane;
    @FXML
    Spinner spinnerA;
    @FXML
    Spinner spinnerB;
    @FXML
    Spinner spinnerC;
    @FXML
    Spinner spinnerD;

    @FXML
    TitledPane valuePane;
    @FXML
    TextField fieldDet;
    @FXML
    TextField transA;
    @FXML
    TextField transB;
    @FXML
    TextField transC;
    @FXML
    TextField transD;
    @FXML
    TextField invA;
    @FXML
    TextField invB;
    @FXML
    TextField invC;
    @FXML
    TextField invD;

    @FXML
    TitledPane controlPane;
    @FXML
    Button btnGenerate;
    @FXML
    Button btnReset;

    @FXML
    Button btnSwitchScene;
    @FXML
    Canvas canvasPane;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");

    }

    private void handleClickMe(Event e) {

    }

    private void loadSecondaryScene(Event e) {

    }
}
