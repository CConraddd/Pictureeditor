package com.example.labb4fix2.Controller;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import com.example.labb4fix2.View.ImageMatrixUtil;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * Utility class responsible for reading and writing image files using JavaFX dialogs.
 */
public class FileIO {

    public static Image readImageFromFile(Window ownerWindow) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(ownerWindow);

        if (file != null) {
            return new Image(file.toURI().toString());
        } else {
            return null;
        }
    }
    /**
     * Opens a FileChooser dialog to save the provided image to the file system as a PNG file.
     *
     * @param image The image to be saved.
     * @param ownerWindow The parent window for the FileChooser dialog.
     */
    public static void writeImageToFile(Image image, Window ownerWindow) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image File");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("PNG files", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(ownerWindow);

        if (file != null) {
            BufferedImage bImage = ImageMatrixUtil.imageToBufferedImage(image);
            try {
                ImageIO.write(bImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}