package edu.vanier.matrixView.controllers;

import edu.vanier.matrixView.UI.aboutUsStage;
import edu.vanier.matrixView.UI.detInfoStage;
import edu.vanier.matrixView.animations.Graphs;
import edu.vanier.matrixView.export.DataExport;
import edu.vanier.matrixView.math.Calculator;
import edu.vanier.matrixView.math.Coordinate;
import edu.vanier.matrixView.math.Matrix;
import edu.vanier.matrixView.math.Vector;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;

/**
 * FXML controller for controlling the main window.
 */
public class MainAppFXMLController {
    public static Matrix userMatrix;
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
    @FXML
    Slider spacingSlider;

    @FXML
    Spinner spinnerB = new Spinner(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

    @FXML
    Spinner spinnerC = new Spinner<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

    @FXML
    Spinner spinnerD = new Spinner<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

    @FXML
    Button detInfoBtn;
    @FXML
    Button resetBtn;
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
    
    double spacing = 30;
    final double DEFAULT_SPACING = 30;
    Matrix initMatrix = new Matrix(1, 0, 0, 1);
    Stage stage;

    Matrix finalPosMtx = initMatrix;

    @FXML
    public void initialize() {


//        Sets desired spinner properties
        setSpinnerProperties();

//        Handles about section
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
        generateGraph();
//      Generates desired graph using matrix input
        btnGenerate.setOnAction(event -> {
           generateGraph();
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

        detInfoBtn.setOnAction(event -> {
            detInfoStage detInfo = new detInfoStage();



        });
        resetBtn.setOnAction(event -> {
            reuseInverseMatrix(width, height);
        });



        
        // handles export button behaviour
        exportButton.setOnAction(event -> {
            DataExport.exportCanvasToPng(stage, canvasPane, userMatrix);
        });

//      Zoom functionality
        spacingSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<?extends Number> observable, Number oldValue, Number newValue){
                GraphicsContext gc = canvasPane.getGraphicsContext2D();
                gc.clearRect(0, 0, canvasPane.getWidth(), canvasPane.getHeight()); // Clear the canvas
                spacing = (double) newValue;
            }
        });


        canvasPane.addEventHandler(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) { // mouse events
                double zoomFactor = (event.getDeltaY() > 0) ? 0.1 : -0.1; // modifying factors

                GraphicsContext gc = canvasPane.getGraphicsContext2D();
                gc.clearRect(0, 0, canvasPane.getWidth(), canvasPane.getHeight()); // Clear the canvas

                gc.save();

                // scale canvas based on the scroll value
                spacing += DEFAULT_SPACING * zoomFactor;
                spacingSlider.setValue(spacing);
                if (spacing <= 0) {
                    spacing = 0.1;
                }

                gc.restore();
            }
        });
    detInfoBtn.setTooltip(new Tooltip("Launch DetInfo"));
    resetBtn.setTooltip(new Tooltip("Reuse inverse for simulation"));

    }
//Method to handle spinner properties
    private void setSpinnerProperties() {
        intWarning.setVisible(false);
        EventHandler<KeyEvent> enterKeyEventHandler;

        spinnerA.setPromptText("Value A");
        spinnerB.setEditable(true);
        spinnerB.setPromptText("Value B");
        spinnerC.setEditable(true);
        spinnerC.setPromptText("Value C");
        spinnerD.setEditable(true);
        spinnerD.setPromptText("Value D");

        createSpinner(spinnerA, -100, 100, 1, 0.5);
        createSpinner(spinnerB, -100, 100, 0, 0.5);
        createSpinner(spinnerC, -100, 100, 0, 0.5);
        createSpinner(spinnerD, -100, 100, 1, 0.5);

    }

    private void reuseInverseMatrix(int width, int height) {
        RotateTransition rt = new RotateTransition(Duration.millis(750), resetBtn.getGraphic());
        userMatrix = Calculator.inverse(userMatrix);
        spinnerA.getValueFactory().setValue(userMatrix.getA());
        spinnerB.getValueFactory().setValue(userMatrix.getB());
        spinnerC.getValueFactory().setValue(userMatrix.getC());
        spinnerD.getValueFactory().setValue(userMatrix.getD());
        drawDefaultSpace(width, height);
        generateGraph();
        rt.setByAngle(-360);
        rt.setCycleCount(1);
        rt.setAutoReverse(false);
        rt.setInterpolator(Interpolator.EASE_IN);
        rt.play();
    }


    //    Method to separate spinners by value factories
    private Spinner<Double> createSpinner(Spinner<Double> spinner, double min, double max, double initialValue, double step) {

        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(min, max, initialValue, step);
        spinner.setValueFactory(valueFactory);
        spinner.setEditable(true);

        spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double value = Double.parseDouble(newValue);
                if (String.valueOf(value).matches("-?\\d*(\\.\\d+)?")) {

                    spinner.getValueFactory().setValue(value);
                }
                 else {
                    spinner.getEditor().setText(oldValue);
                }
            } catch (NumberFormatException e) {
                spinner.getEditor().setText(oldValue);
            }
        });

        return spinner;
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
    private void generateGraph(){
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


