package edu.vanier.matrixView.supportClasses;

import edu.vanier.matrixView.math.Matrix;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import edu.vanier.matrixView.math.Calculator;

public class DataExport {
    /**
     * Exports the content of a JavaFX canvas to a PNG image file. This method
     * opens a file chooser dialog to let the user specify where to save the
     * image. It captures the content of the provided canvas, converts it to an
     * image, and saves it as a PNG file to the selected location.
     *
     * @param ownerStage The primary stage of the application. This is used to
     * show the file chooser dialog.
     * @param canvas The Canvas object whose content is to be exported as a PNG
     * image. This canvas is rendered to an image and saved to the user's
     * selected file location.
     */
    public static void exportCanvasToPng(Stage ownerStage, Canvas canvas, Matrix matrix) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String text = String.valueOf(matrix);
        File selectedFile = openFileChooser(ownerStage, text);

        if (selectedFile != null) {
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            gc.drawImage(writableImage, 0, 0);
            double det = Calculator.determinant(matrix);
            Matrix adj = Calculator.adjugate(matrix);
            Matrix inv = Calculator.adjugate(matrix);
            gc.setLineWidth(0.8);
            gc.setGlobalAlpha(0.5);
            gc.setFill(Color.BLUE);
            gc.setFont(new Font("Comic Sans MS", (canvas.getHeight() + canvas.getWidth()) / 2 * 0.032));
            gc.setFontSmoothingType(FontSmoothingType.LCD);
            gc.fillText("Input matrix: " + String.valueOf(matrix), 0,canvas.getHeight()/4);
            gc.fillText("Determinant: " + String.valueOf(det), 0,canvas.getHeight()/4 + canvas.getHeight()*0.05);
            gc.fillText("Inverse: " + String.valueOf(inv), 0,canvas.getHeight()/4 + canvas.getHeight()*0.1);
            gc.fillText("Adjugate: " + String.valueOf(adj), 0,canvas.getHeight()/4 + canvas.getHeight()*0.15);
            canvas.snapshot(null, writableImage);

            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            try {
                ImageIO.write(bufferedImage, "PNG", selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Opens a file chooser dialog for saving a file. The dialog allows the user
     * to select a directory and specify a file name for saving. It also filters
     * the file types to allow saving of image files (PNG, JPG, GIF) and text
     * files (TXT). The initial directory for the file chooser is set to the
     * user's Desktop, or if the Desktop is unavailable, the user's home
     * directory is used instead.
     *
     * @param ownerStage The primary stage used to open the file chooser. This
     * is typically the main window of the JavaFX application.
     * @return The selected file, or {@code null} if the user cancels the file
     * selection.
     */
    private static File openFileChooser(Stage ownerStage, String text) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().addAll(imageFilter, textFilter);
        fileChooser.setInitialFileName(text);
        File desktopDirectory = new File(System.getProperty("user.home"), "Desktop");
        if (desktopDirectory.exists() && desktopDirectory.isDirectory()) {
            fileChooser.setInitialDirectory(desktopDirectory);
        } else {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }
        return fileChooser.showSaveDialog(ownerStage);
    }

}
