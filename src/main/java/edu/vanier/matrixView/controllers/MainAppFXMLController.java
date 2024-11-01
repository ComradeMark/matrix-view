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
    private TitledPane valuePane;
    @FXML
    private TextField fieldDet;
    @FXML
    private TextField transA;
    @FXML
    private TextField transB;
    @FXML
    private TextField transC;
    @FXML
    private TextField transD;
    @FXML
    private TextField invA;
    @FXML
    private TextField invB;
    @FXML
    private TextField invC;
    @FXML
    private TextField invD;

    @FXML
    private TitledPane controlPane;
    @FXML
    private Button btnGenerate;
    @FXML
    private Button btnReset;

    @FXML
    private Button btnSwitchScene;
    @FXML
    private Canvas canvasPane;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");

    }

    private void handleClickMe(Event e) {

    }

    private void loadSecondaryScene(Event e) {

    }

    public TitledPane getConfigPane() {
        return configPane;
    }

    public void setConfigPane(TitledPane configPane) {
        this.configPane = configPane;
    }

    public Spinner getSpinnerA() {
        return spinnerA;
    }

    public void setSpinnerA(Spinner spinnerA) {
        this.spinnerA = spinnerA;
    }

    public Spinner getSpinnerB() {
        return spinnerB;
    }

    public void setSpinnerB(Spinner spinnerB) {
        this.spinnerB = spinnerB;
    }

    public Spinner getSpinnerC() {
        return spinnerC;
    }

    public void setSpinnerC(Spinner spinnerC) {
        this.spinnerC = spinnerC;
    }

    public Spinner getSpinnerD() {
        return spinnerD;
    }

    public void setSpinnerD(Spinner spinnerD) {
        this.spinnerD = spinnerD;
    }

    public TitledPane getValuePane() {
        return valuePane;
    }

    public void setValuePane(TitledPane valuePane) {
        this.valuePane = valuePane;
    }

    public TextField getFieldDet() {
        return fieldDet;
    }

    public void setFieldDet(TextField fieldDet) {
        this.fieldDet = fieldDet;
    }

    public TextField getTransA() {
        return transA;
    }

    public void setTransA(TextField transA) {
        this.transA = transA;
    }

    public TextField getTransB() {
        return transB;
    }

    public void setTransB(TextField transB) {
        this.transB = transB;
    }

    public TextField getTransC() {
        return transC;
    }

    public void setTransC(TextField transC) {
        this.transC = transC;
    }

    public TextField getTransD() {
        return transD;
    }

    public void setTransD(TextField transD) {
        this.transD = transD;
    }

    public TextField getInvA() {
        return invA;
    }

    public void setInvA(TextField invA) {
        this.invA = invA;
    }

    public TextField getInvB() {
        return invB;
    }

    public void setInvB(TextField invB) {
        this.invB = invB;
    }

    public TextField getInvC() {
        return invC;
    }

    public void setInvC(TextField invC) {
        this.invC = invC;
    }

    public TextField getInvD() {
        return invD;
    }

    public void setInvD(TextField invD) {
        this.invD = invD;
    }

    public TitledPane getControlPane() {
        return controlPane;
    }

    public void setControlPane(TitledPane controlPane) {
        this.controlPane = controlPane;
    }

    public Button getBtnGenerate() {
        return btnGenerate;
    }

    public void setBtnGenerate(Button btnGenerate) {
        this.btnGenerate = btnGenerate;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(Button btnReset) {
        this.btnReset = btnReset;
    }

    public Button getBtnSwitchScene() {
        return btnSwitchScene;
    }

    public void setBtnSwitchScene(Button btnSwitchScene) {
        this.btnSwitchScene = btnSwitchScene;
    }

    public Canvas getCanvasPane() {
        return canvasPane;
    }

    public void setCanvasPane(Canvas canvasPane) {
        this.canvasPane = canvasPane;
    }
    
    
}
