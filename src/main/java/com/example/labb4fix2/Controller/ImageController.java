package com.example.labb4fix2.Controller;

import com.example.labb4fix2.View.*;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

public class ImageController {

    private final Window ownerWindow; // The main application window or any other window
    private Image currentImage;
    private final ImageView imageView;
    private Image originalImage;

    public ImageController(Window ownerWindow, ImageView imageView) {
        this.ownerWindow = ownerWindow;
        this.imageView = imageView;
    }


    public int[][] getCurrentImagePixelMatrix() {
        if (currentImage != null) {
            return ImageMatrixUtil.getPixelMatrixFromImage(currentImage);
        }
        return null;
    }

    public void openImage() {
        System.out.println("Opening image...");
        Image image = FileIO.readImageFromFile(ownerWindow);
        if (image != null) {
            this.currentImage = image;
            this.originalImage = image;  // Set the original image
            // Update view with the new image
            imageView.setImage(currentImage);
        } else {
            System.out.println("Failed to open image or image is null.");
        }
    }
    /**
     * Saves the currently loaded image to the file system.
     */
    public void saveImage() {
        if (currentImage != null) {
            FileIO.writeImageToFile(currentImage, ownerWindow);
        }
    }
    /**
     * Applies a grayscale effect to the currently loaded image.
     */
    public void applyGrayscale() {
        if (currentImage != null) {
            HandleGrayScale processor = new HandleGrayScale(currentImage);
            this.currentImage = processor.getGrayscaleImage();
            imageView.setImage(currentImage);
        }
    }
    /**
     * Inverts the colors of the currently loaded image.
     */
    public void invertImageColors() {
        if (currentImage != null) {
            HandleInvertColors colorInverter = new HandleInvertColors(currentImage);
            this.currentImage = colorInverter.getInvertedColorImg();
            imageView.setImage(currentImage);
        }
    }
    /**
     * Adjusts the window and level of the currently loaded image.
     *
     * @param window Window value for the adjustment.
     * @param level Level value for the adjustment.
     */
    private void adjustWindowLevel(int window, int level) {
        if (originalImage != null) {  // Ensure that the originalImage is not null
            HandleWindowLevel windowLevel = new HandleWindowLevel(originalImage, window, level);
            this.currentImage = windowLevel.getWindowLevelImg();
            imageView.setImage(currentImage);
        }
    }

    /**
     * Provides sliders to adjust the window and level of the image.
     *
     * @param windowSlider Slider to adjust window value.
     * @param levelSlider Slider to adjust level value.
     * @param windowLabel Label to display the current window value.
     * @param levelLabel Label to display the current level value.
     */
    public void displayWindowLevelValues(Slider windowSlider, Slider levelSlider,
                                         Label windowLabel, Label levelLabel) {
        windowSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int window = (int) windowSlider.getValue();
            int level = (int) levelSlider.getValue();
            windowLabel.setText(String.valueOf(window));
            adjustWindowLevel(window, level);
        });

        levelSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int window = (int) windowSlider.getValue();
            int level = (int) levelSlider.getValue();
            levelLabel.setText(String.valueOf(level));
            adjustWindowLevel(window, level);
        });
    }
    /**
     * Displays histogram controls and the histogram of the image.
     *
     * @param mainLayout The main layout to which histogram controls are added.
     */

    public void setButtonsAndShowHistogram(BorderPane mainLayout) {
        HandleHistogram histogram = new HandleHistogram(getCurrentImage());
        VBox controls = new VBox(10);
        controls.setPrefWidth(400);

        VBox histogramDisplay = new VBox();
        Button redButton = histogram.createColoredButton("Red", Color.RED);
        Button greenButton = histogram.createColoredButton("Green", Color.GREEN);
        Button blueButton = histogram.createColoredButton("Blue", Color.BLUE);

        HBox buttonsHBox = new HBox(10, redButton, greenButton, blueButton);

        controls.getChildren().addAll(histogramDisplay, buttonsHBox);
        mainLayout.setLeft(controls);

        redButton.setOnAction(e -> {
            int[][] histogramData = histogram.calculateHistogram();
            histogram.showHistogram("Red", histogramData[0], histogramDisplay);
        });

        greenButton.setOnAction(e -> {
            int[][] histogramData = histogram.calculateHistogram();
            histogram.showHistogram("Green", histogramData[1], histogramDisplay);
        });

        blueButton.setOnAction(e -> {
            int[][] histogramData = histogram.calculateHistogram();
            histogram.showHistogram("Blue", histogramData[2], histogramDisplay);
        });
    }
    /**
     * Displays an error alert dialog.
     *
     * @param msg Main error message to be displayed.
     * @param content Additional content or context for the error.
     */
    public void showErrorAlert(String msg, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error while " + content + " :" + msg);

        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }
    /**
     * Retrieves the currently loaded image.
     *
     * @return The current Image object.
     */
    public Image getCurrentImage() {
        return currentImage;
    }
}
