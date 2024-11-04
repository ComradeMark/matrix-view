package edu.vanier.matrixView.controllers;

import edu.vanier.matrixView.MainApp;
import edu.vanier.matrixView.UI.aboutUsStage;
import edu.vanier.matrixView.animations.Graphs;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML controller for controlling the main window.
 *
 */
public class MainAppFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);

    @FXML
    BorderPane mainPanel;

    public BorderPane getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(BorderPane mainPanel) {
        this.mainPanel = mainPanel;
    }

    public SpinnerValueFactory<Integer> getSpinnerAProperties() {
        return spinnerAProperties;
    }

    public void setSpinnerAProperties(SpinnerValueFactory<Integer> spinnerAProperties) {
        this.spinnerAProperties = spinnerAProperties;
    }

    public SpinnerValueFactory<Integer> getSpinnerBProperties() {
        return spinnerBProperties;
    }

    public void setSpinnerBProperties(SpinnerValueFactory<Integer> spinnerBProperties) {
        this.spinnerBProperties = spinnerBProperties;
    }

    public SpinnerValueFactory<Integer> getSpinnerCProperties() {
        return spinnerCProperties;
    }

    public void setSpinnerCProperties(SpinnerValueFactory<Integer> spinnerCProperties) {
        this.spinnerCProperties = spinnerCProperties;
    }

    public SpinnerValueFactory<Integer> getSpinnerDProperties() {
        return spinnerDProperties;
    }

    public void setSpinnerDProperties(SpinnerValueFactory<Integer> spinnerDProperties) {
        this.spinnerDProperties = spinnerDProperties;
    }

    public Text getIntWarning() {
        return intWarning;
    }

    public void setIntWarning(Text intWarning) {
        this.intWarning = intWarning;
    }

    public Button getBtnAbout() {
        return btnAbout;
    }

    public void setBtnAbout(Button btnAbout) {
        this.btnAbout = btnAbout;
    }

    public Pane getGraphPane() {
        return graphPane;
    }

    public void setGraphPane(Pane graphPane) {
        this.graphPane = graphPane;
    }
    @FXML
    TitledPane configPane;
    @FXML
    Spinner spinnerA = new Spinner(-1000, 1000, 0);
    ;
    SpinnerValueFactory<Integer> spinnerAProperties = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

    @FXML
    Spinner spinnerB = new Spinner(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    SpinnerValueFactory<Integer> spinnerBProperties = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);


    @FXML
    Spinner spinnerC = new Spinner<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    SpinnerValueFactory<Integer> spinnerCProperties = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);


    @FXML
    Spinner spinnerD = new Spinner<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    SpinnerValueFactory<Integer> spinnerDProperties = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);


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
    private Text intWarning;

    @FXML
    private TitledPane controlPane;
    @FXML
    private Button btnGenerate;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnAbout;
    @FXML
    private Pane graphPane;

    @FXML
    private Button btnSwitchScene;
    @FXML
    private Canvas canvasPane;
    protected static final String INITAL_VALUE = "0";

    @FXML
    public void initialize() {


        intWarning.setVisible(false);
        EventHandler<KeyEvent> enterKeyEventHandler;

        enterKeyEventHandler = new EventHandler<KeyEvent>() {


            // Handles checking whether a manual user entry into field is an integer or not, and resets entry if it is invalid. Uses animation.
            @Override
            public void handle(KeyEvent event) {

                // handle users "enter key event"
                if (event.getCode() == KeyCode.ENTER) {

                    try {
                        Integer.parseInt(spinnerA.getPromptText());
                    } catch (NumberFormatException e) {
                        intWarning.setVisible(true);
                        PauseTransition intError = new PauseTransition(
                                Duration.seconds(3)
                        );
                        intError.setOnFinished(
                                event1 -> intWarning.setVisible(false)
                        );
                        intError.play();

                        // reset editor to INITAL_VALUE
                        spinnerA.getEditor().textProperty().set(INITAL_VALUE);
                        spinnerB.getEditor().textProperty().set(INITAL_VALUE);
                        spinnerC.getEditor().textProperty().set(INITAL_VALUE);
                        spinnerD.getEditor().textProperty().set(INITAL_VALUE);


                    }
                }
            }
        };







        spinnerA.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE,
                Integer.parseInt(INITAL_VALUE)));
        spinnerA.setEditable(true);
        spinnerA.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);

        spinnerB.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE,
                Integer.parseInt(INITAL_VALUE)));
        spinnerB.setEditable(true);
        spinnerB.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);
        spinnerC.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE,
                Integer.parseInt(INITAL_VALUE)));
        spinnerC.setEditable(true);
        spinnerC.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);
        spinnerD.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE,
                Integer.parseInt(INITAL_VALUE)));
        spinnerD.setEditable(true);
        spinnerD.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);





        spinnerA.setPromptText("Value A");
                spinnerB.setEditable(true);
                spinnerB.setPromptText("Value B");
                spinnerC.setEditable(true);
                spinnerC.setPromptText("Value C");
                spinnerD.setEditable(true);
                spinnerD.setPromptText("Value D");

                btnAbout.setOnAction((event ->

            {
                aboutUsStage aboutUs = new aboutUsStage();


            }));



        // All graph insertion code
        Graphs mainGraph = new Graphs();
//        canvasPane = mainGraph.getGraph(400, 400);

        int width = (int)canvasPane.getWidth();
        int height = (int)canvasPane.getHeight();

        graphPane.getChildren().set(0, mainGraph.getGraph(width, height));
        System.out.println(graphPane.getHeight());
        spinnerA.setValueFactory(spinnerAProperties);
        spinnerB.setValueFactory(spinnerBProperties);
        spinnerC.setValueFactory(spinnerCProperties);
        spinnerD.setValueFactory(spinnerDProperties);

        
    
        
        }


        private void handleClickMe (Event e){


        }

        private void loadSecondaryScene (Event e){

        }

        public TitledPane getConfigPane () {
            return configPane;
        }

        public void setConfigPane (TitledPane configPane){
            this.configPane = configPane;
        }

        public Spinner getSpinnerA () {
            return spinnerA;
        }

        public void setSpinnerA (Spinner spinnerA){
            this.spinnerA = spinnerA;
        }

        public Spinner getSpinnerB () {
            return spinnerB;
        }

        public void setSpinnerB (Spinner spinnerB){
            this.spinnerB = spinnerB;
        }

        public Spinner getSpinnerC () {
            return spinnerC;
        }

        public void setSpinnerC (Spinner spinnerC){
            this.spinnerC = spinnerC;
        }

        public Spinner getSpinnerD () {
            return spinnerD;
        }

        public void setSpinnerD (Spinner spinnerD){
            this.spinnerD = spinnerD;
        }

        public TitledPane getValuePane () {
            return valuePane;
        }

        public void setValuePane (TitledPane valuePane){
            this.valuePane = valuePane;
        }

        public TextField getFieldDet () {
            return fieldDet;
        }

        public void setFieldDet (TextField fieldDet){
            this.fieldDet = fieldDet;
        }

        public TextField getTransA () {
            return transA;
        }

        public void setTransA (TextField transA){
            this.transA = transA;
        }

        public TextField getTransB () {
            return transB;
        }

        public void setTransB (TextField transB){
            this.transB = transB;
        }

        public TextField getTransC () {
            return transC;
        }

        public void setTransC (TextField transC){
            this.transC = transC;
        }

        public TextField getTransD () {
            return transD;
        }

        public void setTransD (TextField transD){
            this.transD = transD;
        }

        public TextField getInvA () {
            return invA;
        }

        public void setInvA (TextField invA){
            this.invA = invA;
        }

        public TextField getInvB () {
            return invB;
        }

        public void setInvB (TextField invB){
            this.invB = invB;
        }

        public TextField getInvC () {
            return invC;
        }

        public void setInvC (TextField invC){
            this.invC = invC;
        }

        public TextField getInvD () {
            return invD;
        }

        public void setInvD (TextField invD){
            this.invD = invD;
        }

        public TitledPane getControlPane () {
            return controlPane;
        }

        public void setControlPane (TitledPane controlPane){
            this.controlPane = controlPane;
        }

        public Button getBtnGenerate () {
            return btnGenerate;
        }

        public void setBtnGenerate (Button btnGenerate){
            this.btnGenerate = btnGenerate;
        }

        public Button getBtnReset () {
            return btnReset;
        }

        public void setBtnReset (Button btnReset){
            this.btnReset = btnReset;
        }

        public Button getBtnSwitchScene () {
            return btnSwitchScene;
        }

        public void setBtnSwitchScene (Button btnSwitchScene){
            this.btnSwitchScene = btnSwitchScene;
        }

        public Canvas getCanvasPane () {
            return canvasPane;
        }

        public void setCanvasPane (Canvas canvasPane){
            this.canvasPane = canvasPane;
        }


    }


