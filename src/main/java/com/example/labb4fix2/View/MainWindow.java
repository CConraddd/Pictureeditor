package com.example.labb4fix2.View;

import javafx.scene.control.*;
import com.example.labb4fix2.Controller.ImageController;
import com.example.labb4fix2.Model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * The main window for the image editing application.
 * It provides user interface elements to apply various image processing functions such as grayscale, invert colors, etc.
 */
public class MainWindow extends Application {

    private ImageController controller;
    private final ImageView imageView = new ImageView();

    /**
     * Starts the JavaFX application and sets up the UI elements.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        controller = new ImageController(primaryStage, imageView);

        BorderPane mainLayout = new BorderPane();

        mainLayout.setRight(imageView);

        VBox controls = new VBox(10);
        controls.setPrefWidth(500);

        Slider windowSlider = new Slider(0, 255, 127);
        windowSlider.setBlockIncrement(1);
        windowSlider.setMajorTickUnit(32);
        windowSlider.setMinorTickCount(0);
        windowSlider.setShowTickLabels(true);
        windowSlider.setShowTickMarks(true);

        Slider levelSlider = new Slider(0, 255, 127);
        levelSlider.setBlockIncrement(1);
        levelSlider.setMajorTickUnit(32);
        levelSlider.setMinorTickCount(0);
        levelSlider.setShowTickLabels(true);
        levelSlider.setShowTickMarks(true);

        windowSlider.setPrefWidth(400);
        levelSlider.setPrefWidth(400);

        VBox slidersBox = new VBox(
                new Label("Window:"),
                windowSlider,
                new Label("Level:"),
                levelSlider
        );
        Label levelValueLabel = new Label("Level: ");
        Label windowValueLabel = new Label("Window: ");
        Label levelLabel = new Label("127");
        Label windowLabel = new Label("127");

        HBox sliderLabels = new HBox(20,
                new HBox(windowValueLabel, windowLabel),
                new HBox(levelValueLabel, levelLabel)
        );

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(e -> controller.openImage());
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> System.exit(0));
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(e -> {
            try {
                if (controller.getCurrentImagePixelMatrix() == null) {
                    throw new NoImageFoundException("No image found");
                } else {
                    controller.saveImage();
                }
            } catch (NoImageFoundException ex) {
                controller.showErrorAlert(ex.getMessage(), "saving image");
            } catch (RuntimeException ex) {
                controller.showErrorAlert("An error occurred: " + ex.getMessage(), "");
            }
        });
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, exitMenuItem);


        Menu editMenu = new Menu("Generate");
        MenuItem grayscaleMenuItem = new MenuItem("Grayscale");
        grayscaleMenuItem.setOnAction(e -> {
            try {
                if (controller.getCurrentImagePixelMatrix() == null) {
                    throw new NoImageFoundException(" No image found");
                } else {
                    controller.applyGrayscale();
                }
            } catch (NoImageFoundException ex) {
                controller.showErrorAlert(ex.getMessage(), "applying Grayscale");
            } catch (RuntimeException ex) {
                controller.showErrorAlert("An error occurred: " + ex.getMessage(), "");
            }

        });
        MenuItem invertColorsMenuItem = new MenuItem("Invert Colors");
        invertColorsMenuItem.setOnAction(e -> {
            try {
                if (controller.getCurrentImagePixelMatrix() == null) {
                    throw new NoImageFoundException("No image found");
                } else {
                    controller.invertImageColors();
                }
            } catch (NoImageFoundException ex) {
                controller.showErrorAlert(ex.getMessage(), "inverting colors");
            } catch (RuntimeException ex) {
                controller.showErrorAlert("An error occurred: " + ex.getMessage(), "");
            }
        });
        editMenu.getItems().add(invertColorsMenuItem);


        MenuItem windowLevelMenuItem = new MenuItem("Adjust Window/Level");
        windowLevelMenuItem.setOnAction(e -> {
            try {
                if (controller.getCurrentImagePixelMatrix() == null) {
                    throw new NoImageFoundException("No image found");
                } else {

                    controller.displayWindowLevelValues(windowSlider, levelSlider, windowLabel, levelLabel);
                    mainLayout.setLeft(slidersBox);
                    mainLayout.setBottom(sliderLabels);
                }
            } catch (NoImageFoundException ex) {
                controller.showErrorAlert(ex.getMessage(), "applying Window/Level");
            } catch (RuntimeException ex) {
                controller.showErrorAlert("An error occurred:" + ex.getMessage(), "");
            }
        });
        MenuItem histogramMenuItem = new MenuItem("Histogram");
        histogramMenuItem.setOnAction(e -> {
            try {
                if (controller.getCurrentImagePixelMatrix() == null) {
                    throw new NoImageFoundException("No image found");
                } else {
                    controller.setButtonsAndShowHistogram(mainLayout);
                }
            } catch (NoImageFoundException ex) {
                controller.showErrorAlert(ex.getMessage(), "applying Histogram");
            } catch (RuntimeException ex) {
                controller.showErrorAlert("An error occurred:" + ex.getMessage(), "");
            }
        });

        editMenu.getItems().addAll(grayscaleMenuItem, windowLevelMenuItem, histogramMenuItem);

        menuBar.getMenus().addAll(fileMenu, editMenu);
        mainLayout.setTop(menuBar);

        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setTitle("Image Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * The main entry point for the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}