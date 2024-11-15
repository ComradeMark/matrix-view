package edu.vanier.matrixView.export;
import edu.vanier.matrixView.animations.Graphs;
import edu.vanier.matrixView.math.Matrix;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
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
        // Open the file chooser and get the selected file path
        int width = (int) Math.ceil(canvas.getWidth());
        int height = (int) Math.ceil(canvas.getWidth());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String text = String.valueOf(matrix);
        File selectedFile = openFileChooser(ownerStage, text);

        if (selectedFile != null) {
            // Create a WritableImage with the same dimensions as the canvas
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());

            gc.drawImage(writableImage, 0, 0);
            double det = Calculator.determinant(matrix);
            Matrix adj = Calculator.adjugate(matrix);
            Matrix inv = Calculator.adjugate(matrix);
            gc.strokeText(String.valueOf(matrix), 100,100);
            gc.strokeText(String.valueOf(det), 200,200);
            gc.strokeText(String.valueOf(inv), 300,300);
            gc.strokeText(String.valueOf(adj), 400,400);
            // Capture the content of the canvas into the WritableImage
            canvas.snapshot(null, writableImage);

            // Convert the WritableImage to a BufferedImage
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            Graphs graphs = new Graphs(matrix);
            graphs.drawGraph(width, height, canvas, Color.BLACK, Color.GRAY, 20);
            // Todo: there is a problem of color matching when it comes to lines
            graphs.drawDefaultSpace(width, height, canvas);

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
        // Add file filters (optional)
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().addAll(imageFilter, textFilter);
        fileChooser.setInitialFileName(text);  // Default file name

        // Set the initial directory of the FileChooser to the user's Desktop
        File desktopDirectory = new File(System.getProperty("user.home"), "Desktop");
        if (desktopDirectory.exists() && desktopDirectory.isDirectory()) {
            fileChooser.setInitialDirectory(desktopDirectory);
        } else {
            // Fallback to the user's home directory if Desktop is unavailable
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }

        // Show the file chooser and get the selected file
        return fileChooser.showSaveDialog(ownerStage);
    }

}
