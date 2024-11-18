package edu.vanier.matrixView.animations;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

/**
 * Used resources:
 * 1. https://www.youtube.com/watch?v=SiPfsZA_GeI
 * 2. https://stackoverflow.com/questions/12523033/how-do-you-zoom-in-on-a-javafx-2-canvas-node
 * 3. https://stackoverflow.com/questions/2142535/how-to-clear-the-canvas-for-redrawing?rq=2
 * 4. https://stackoverflow.com/questions/16680295/javafx-correct-scaling/16682180#16682180
 * 5. https://stackoverflow.com/questions/2142535/how-to-clear-the-canvas-for-redrawing?rq=2
 */
public class ZoomableCanvas extends Canvas {
    private double scale = 1.0;

    /**
     * Implements Canvas as superclass since we are modifying canvas
     *
     * @param width  width of canvas
     * @param height height of canvas
     */
    public ZoomableCanvas(double width, double height) {
        super(width, height);

        // event handler for mouse scrolling
        this.addEventHandler(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) { // mouse events
                double zoomFactor = (event.getDeltaY() > 0) ? 1.1 : 0.9; // modifying factors
                scale *= zoomFactor;

                GraphicsContext gc = this.getGraphicsContext2D();
                gc.clearRect(0, 0, getWidth(), getHeight()); // Clear the canvas

                gc.save();

                // scale canvas based on the scroll value
                this.setScaleY(scale);
                this.setScaleX(scale);

                gc.restore();
            }
        });
    }
}
