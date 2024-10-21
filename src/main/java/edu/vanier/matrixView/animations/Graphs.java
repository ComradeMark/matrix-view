package edu.vanier.matrixView.animations;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.function.Function;

public class Graphs extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        Plot plot = plotChart(1);

        StackPane layout = new StackPane(
                plot
        );
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: rgb(35, 39, 50);");
        layout.setOnScroll(new ZoomHandler());

        stage.setTitle("Matrix View");
        stage.setScene(new Scene(layout, Color.rgb(35, 39, 50)));
        stage.show();
    }

    private Plot plotChart(double zoomFactor) {
        Axes axes = new Axes(
                400, 300,
                // Playing around with these values increases the size of the graph
                -10 * zoomFactor, 10 * zoomFactor, 1,
                -8 * zoomFactor, 8 * zoomFactor, 1
        );

        // We can get the user vectors and draw them as a plot
        Plot plot = new Plot(
                x -> .25 * (x + 4) * (x + 1) * (x - 2),
                -8 * zoomFactor, 8 * zoomFactor, 0.1,
                axes
        );

        return plot;
    }

    class Axes extends Pane {
        private NumberAxis xAxis;
        private NumberAxis yAxis;

        public Axes(
                int width, int height,
                double xLow, double xHi, double xTickUnit,
                double yLow, double yHi, double yTickUnit
        ) {
            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(width, height);
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

            xAxis = new NumberAxis(xLow, xHi, xTickUnit);
            xAxis.setSide(Side.BOTTOM);
            xAxis.setMinorTickVisible(false);
            xAxis.setPrefWidth(width);
            xAxis.setLayoutY(height / 2);

            yAxis = new NumberAxis(yLow, yHi, yTickUnit);
            yAxis.setSide(Side.LEFT);
            yAxis.setMinorTickVisible(false);
            yAxis.setPrefHeight(height);
            yAxis.layoutXProperty().bind(
                    Bindings.subtract(
                            (width / 2) + 1,
                            yAxis.widthProperty()
                    )
            );

            getChildren().setAll(xAxis, yAxis);
        }

        public NumberAxis getXAxis() {
            return xAxis;
        }

        public NumberAxis getYAxis() {
            return yAxis;
        }
    }

    class Plot extends Pane {
        public Plot(
                Function<Double, Double> f,
                double xMin, double xMax, double xInc,
                Axes axes
        ) {
            Path path = new Path();
            // plays around with a given color
            path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
            path.setStrokeWidth(2);

            path.setClip(
                    new Rectangle(
                            0, 0,
                            axes.getPrefWidth(),
                            axes.getPrefHeight()
                    )
            );

            double x = xMin;
            double y = f.apply(x);

            path.getElements().add(
                    new MoveTo(
                            mapX(x, axes), mapY(y, axes)
                    )
            );

            x += xInc;
            while (x < xMax) {
                y = f.apply(x);

                path.getElements().add(
                        new LineTo(
                                mapX(x, axes), mapY(y, axes)
                        )
                );

                x += xInc;
            }

            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

            getChildren().setAll(axes, path);
        }

        private double mapX(double x, Axes axes) {
            double tx = axes.getPrefWidth() / 2;
            double sx = axes.getPrefWidth() /
                    (axes.getXAxis().getUpperBound() -
                            axes.getXAxis().getLowerBound());

            return x * sx + tx;
        }

        private double mapY(double y, Axes axes) {
            double ty = axes.getPrefHeight() / 2;
            double sy = axes.getPrefHeight() /
                    (axes.getYAxis().getUpperBound() -
                            axes.getYAxis().getLowerBound());

            return -y * sy + ty;
        }
    }

    private class ZoomHandler implements EventHandler<ScrollEvent> {
        private static final double MAX_ZOOM = 2;
        private static final double MIN_ZOOM = 0.5;

        private double zoomFactor = 1;

        @Override
        public void handle(ScrollEvent event) {
            if (event.getDeltaY() == 0) {
                return;
            } else if (event.getDeltaY() < 0) {
                zoomFactor = Math.max(MIN_ZOOM, zoomFactor * 0.9);
            } else if (event.getDeltaY() > 0) {
                zoomFactor = Math.min(MAX_ZOOM, zoomFactor * 1.1);
            }

            Plot plot = plotChart(zoomFactor);

            Pane parent = (Pane) event.getSource();
            parent.getChildren().setAll(plot);
        }
    }
}