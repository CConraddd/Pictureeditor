package com.example.labb4fix2.View;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import java.awt.image.BufferedImage;
/**
 * Utility class for converting between {@link Image} objects and their pixel matrix representations.
 * Also provides methods for converting between JavaFX {@link Image} objects and AWT {@link BufferedImage} objects.
 */
public class ImageMatrixUtil {
    /**
     * Converts a given JavaFX {@link Image} into a 2D pixel matrix.
     * Each entry in the matrix corresponds to the ARGB value of the pixel.
     *
     * @param image The JavaFX image to be converted.
     * @return 2D matrix of ARGB values representing the image.
     */
    public static int[][] getPixelMatrixFromImage(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        int[][] pixelMatrix = new int[width][height];
        PixelReader reader = image.getPixelReader();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // store alpha, red, green, blue, each one byte, in an int (four bytes in Java)
                pixelMatrix[x][y] = reader.getArgb(x, y);
            }
        }
        return pixelMatrix;
    }
    /**
     * Converts a 2D pixel matrix into a JavaFX {@link Image}.
     * Each entry in the matrix should be an ARGB value of the pixel.
     *
     * @param pixelMatrix 2D matrix of ARGB values.
     * @return The JavaFX image constructed from the pixel matrix.
     */
    public static Image getImageFromPixelMatrix(int[][] pixelMatrix) {
        int width = pixelMatrix.length;
        int height = pixelMatrix[0].length; // Assuming the matrix is non-empty and rectangular

        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixelWriter.setArgb(x, y, pixelMatrix[x][y]);
            }
        }

        return writableImage;
    }
    /**
     * Converts a JavaFX {@link Image} into an AWT {@link BufferedImage}.
     *
     * @param image The JavaFX image to be converted.
     * @return The AWT buffered image equivalent of the given JavaFX image.
     */
    public static BufferedImage imageToBufferedImage(Image image) {
        return SwingFXUtils.fromFXImage(image, null);
    }
}