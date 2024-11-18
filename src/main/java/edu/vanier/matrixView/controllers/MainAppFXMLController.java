package edu.vanier.matrixView.controllers;

import edu.vanier.matrixView.UI.aboutUsStage;
import edu.vanier.matrixView.animations.Graphs;
import edu.vanier.matrixView.export.DataExport;
import edu.vanier.matrixView.math.Calculator;
import edu.vanier.matrixView.math.Coordinate;
import edu.vanier.matrixView.math.Matrix;
import edu.vanier.matrixView.math.Vector;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;

/**
 * FXML controller for controlling the main window.
 */
public class MainAppFXMLController {
    GraphicsContext ugraph;
    Graphs userGraph;

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);

    @FXML
    BorderPane mainContainer;
    @FXML
    Button exportButton;
    @FXML
    TitledPane configPane;
    @FXML
    Spinner spinnerA = new Spinner(-1000, 1000, 1.0);
    SpinnerValueFactory<Double> spinnerAProperties = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 1.0);

    @FXML
    Spinner spinnerB = new Spinner(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    SpinnerValueFactory<Double> spinnerBProperties = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.0);

    @FXML
    Spinner spinnerC = new Spinner<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    SpinnerValueFactory<Double> spinnerCProperties = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.0);

    @FXML
    Spinner spinnerD = new Spinner<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    SpinnerValueFactory<Double> spinnerDProperties = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 1.0);


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
    
    private AnimationTimer animationTimer;   
    private long lastUpdate = 0;
    
    int spacing = 30;
    Matrix initMatrix = new Matrix(1, 0, 0, 1);
    Stage stage;
    Matrix userMatrix;

    Matrix finalPosMtx = initMatrix;

    private double scale = 1.0;


    @FXML
    public void initialize() {

        intWarning.setVisible(false);
        EventHandler<KeyEvent> enterKeyEventHandler;

        enterKeyEventHandler = new EventHandler<KeyEvent>() {


            // Handles checking whether a manual user entry into field is an integer or not, and resets entry if it is invalid. Uses animation.
            @Override
            public void handle(KeyEvent event) {

                if (
                    spinnerA.getValue().toString().matches("[a-zA-Z]") ||
                    spinnerB.getValue().toString().matches("[a-zA-Z]") ||
                    spinnerC.getValue().toString().matches("[a-zA-Z]") ||
                    spinnerD.getValue().toString().matches("[a-zA-Z]")
                ) {

                    // reset editor to INITAL_VALUE
                    spinnerA.getEditor().textProperty().set(INITAL_VALUE);
                    spinnerB.getEditor().textProperty().set(INITAL_VALUE);
                    spinnerC.getEditor().textProperty().set(INITAL_VALUE);
                    spinnerD.getEditor().textProperty().set(INITAL_VALUE);
                    intWarning.setVisible(true);

                }

                // handle users "enter key event"
                if (event.getCode() == KeyCode.ENTER) {

                    try {
                        Double.parseDouble(spinnerA.getPromptText());
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

        spinnerA.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE,
                Double.parseDouble(INITAL_VALUE)));
        spinnerA.setEditable(true);
        spinnerA.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);

        spinnerB.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE,
                Double.parseDouble(INITAL_VALUE)));
        spinnerB.setEditable(true);
        spinnerB.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);
        spinnerC.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE,
                Double.parseDouble(INITAL_VALUE)));
        spinnerC.setEditable(true);
        spinnerC.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);
        spinnerD.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE,
                Double.parseDouble(INITAL_VALUE)));
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
        int width = (int) canvasPane.getWidth();
        int height = (int) canvasPane.getHeight();

        userGraph = drawDefaultSpace(width, height);

        Vector v = new Vector(spacing, spacing);
        Coordinate coord = new Coordinate(-1 * spacing, -1 * spacing);
        ArrayList<Coordinate> initShit = new ArrayList<>();
        initShit.add(v);
        initShit.add(coord);
        userGraph.drawShit(initShit, canvasPane);
        System.out.println(graphPane.getHeight());

        spinnerA.setValueFactory(spinnerAProperties);
        spinnerB.setValueFactory(spinnerBProperties);
        spinnerC.setValueFactory(spinnerCProperties);
        spinnerD.setValueFactory(spinnerDProperties);



//      Generates desired graph using matrix input
        btnGenerate.setOnAction(event -> {
            double a = (double) spinnerA.getValue();
            double b = (double) spinnerB.getValue();
            double c = (double) spinnerC.getValue();
            double d = (double) spinnerD.getValue();

            userMatrix = new Matrix(a, b, c, d);
            userGraph = new Graphs(initMatrix);
            
            setupAnimation();
            animationTimer.start();

            setupAnimation();
            animationTimer.start();

            fieldDet.setText(String.valueOf(Calculator.determinant(userMatrix)));

            transA.setText(String.valueOf(Calculator.adjugate(userMatrix).getA()));
            transB.setText(String.valueOf(Calculator.adjugate(userMatrix).getB()));
            transC.setText(String.valueOf(Calculator.adjugate(userMatrix).getC()));
            transD.setText(String.valueOf(Calculator.adjugate(userMatrix).getD()));

            invA.setText(String.valueOf(Calculator.inverse(userMatrix).getA()));
            invB.setText(String.valueOf(Calculator.inverse(userMatrix).getB()));
            invC.setText(String.valueOf(Calculator.inverse(userMatrix).getC()));
            invD.setText(String.valueOf(Calculator.inverse(userMatrix).getD()));
        });
        //      Handles reset button behaviour
        btnReset.setOnAction(event -> {
            animationTimer.stop();
            userGraph.removeGraph(canvasPane.getGraphicsContext2D());
            userMatrix = initMatrix;

            userGraph.drawDefaultSpace(width, height, canvasPane);
            fieldDet.setText(String.valueOf(Calculator.determinant(userMatrix)));

            transA.setText("");
            transB.setText("");
            transC.setText("");
            transD.setText("");

            invA.setText("");
            invB.setText("");
            invC.setText("");
            invD.setText("");
        });
        
        // handles export button behaviour
        exportButton.setOnAction(event -> {
            DataExport.exportCanvasToPng(stage, canvasPane, userMatrix);
        });


        canvasPane.addEventHandler(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) { // mouse events
                double zoomFactor = (event.getDeltaY() > 0) ? 1.1 : 0.9; // modifying factors
                scale *= zoomFactor;

                GraphicsContext gc = canvasPane.getGraphicsContext2D();
                gc.clearRect(0, 0, canvasPane.getWidth(), canvasPane.getHeight()); // Clear the canvas

                gc.save();

                // scale canvas based on the scroll value
                canvasPane.setScaleY(scale);
                canvasPane.setScaleX(scale);

                gc.restore();
            }
        });


    }

//      Draws default euclidean space
    public Graphs drawDefaultSpace(int width, int height){
        Matrix simpleBasis = initMatrix;
        // All graph insertion code
        Graphs mainGraph = new Graphs(simpleBasis);

        mainGraph.drawGraph(width, height, canvasPane, Color.BLACK, Color.LIGHTSLATEGREY, spacing);
        return mainGraph;
    }
    
    private void setupAnimation() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                if(lastUpdate > 0){
                    double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                    
                    updateAnimation(deltaTime);
                }
                lastUpdate = now;
            }
        };
    }
    
    private void updateAnimation(double deltaTime){
        userGraph.removeGraph(canvasPane.getGraphicsContext2D());
        Matrix moveMtx = Calculator.matrixSubtract(userMatrix, finalPosMtx);
        Matrix scaledMoveMtx = Calculator.scalarMult(deltaTime, moveMtx);
        finalPosMtx = Calculator.matrixAdd(finalPosMtx, scaledMoveMtx);
        int width = (int) canvasPane.getWidth();
        int height = (int) canvasPane.getHeight();
        userGraph = new Graphs(finalPosMtx);
        ugraph = userGraph.drawGraph(width, height, canvasPane, Color.RED, Color.LIGHTSLATEGREY, spacing);

        Vector v = new Vector(spacing, spacing);
        Coordinate coord = new Coordinate(-1 * spacing, -1 * spacing);
        ArrayList<Coordinate> initShit = new ArrayList<>();
        initShit.add(v);
        initShit.add(coord);
        userGraph.drawShit(initShit, canvasPane);

        if (Math.abs(scaledMoveMtx.getA() + scaledMoveMtx.getB() + scaledMoveMtx.getC() + scaledMoveMtx.getD()) < 10000){
            animationTimer.stop();
        }
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


