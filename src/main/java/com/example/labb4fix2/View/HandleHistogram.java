package com.example.labb4fix2.View;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.example.labb4fix2.Model.HistogramCalculator;
/**
 * Handler for generating and displaying the histogram of an image.
 * This class leverages the {@link HistogramCalculator} to calculate histogram values
 * and then visualizes those values using JavaFX charts.
 */
public class HandleHistogram {
    private Image originalImage;
    private HistogramCalculator calculator;
    /**
     * Constructs a HandleHistogram object with the specified image.
     *
     * @param originalImage The original image whose histogram needs to be generated.
     */
    public HandleHistogram(Image originalImage){
        this.originalImage = originalImage;
        calculator = new HistogramCalculator();
    }
    /**
     * Calculates the histogram for the original image.
     *
     * @return A 2D array representing the histogram, where each row corresponds
     *         to one of the RGB channels (in the order: Red, Green, Blue).
     */
    public int [][] calculateHistogram(){
        return
                calculator.processImage(ImageMatrixUtil.getPixelMatrixFromImage(originalImage));
    }
    /**
     * Creates a histogram bar chart for the given data and color channel.
     *
     * @param data The histogram data for the color channel.
     * @param color The name of the color channel (e.g., "Red").
     * @return A bar chart visualizing the histogram data.
     */
    private BarChart<String, Number> createHistogramBarChart(int[] data, String color) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Ensure the y-axis does not show negative values and starts from 0
        yAxis.setForceZeroInRange(true);
        yAxis.setAutoRanging(true);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(color + " Histogram");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (int i = 0; i < data.length; i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), data[i]));
        }
        barChart.getData().addAll(series);
        return barChart;
    }
    /**
     * Creates a button with a specified color and name.
     * The button features a hollow circle of the specified color and a label.
     *
     * @param colorName The name of the color
     * @param color The JavaFX Color object representing the color.
     * @return A button with a colored circle and label.
     */
    public Button createColoredButton(String colorName, Color color) {
        Circle circle = new Circle(10);
        circle.setStroke(color);
        circle.setFill(Color.TRANSPARENT); // Making the circle hollow
        circle.setStrokeWidth(2);

        Label colorLabel = new Label(colorName);
        HBox buttonContent = new HBox(5, circle, colorLabel); // Creating a layout to hold the circle and the name
        Button button = new Button();
        button.setGraphic(buttonContent); // Setting the button's graphic content
        return button;
    }
    /**
     * Displays the histogram chart for a specified color channel in a given display area.
     *
     * @param color The name of the color channel (e.g., "Red").
     * @param data The histogram data for the color channel.
     * @param displayArea The JavaFX container where the chart should be displayed.
     */
    public void showHistogram(String color, int[] data, VBox displayArea) {
        BarChart<String, Number> chart = createHistogramBarChart(data, color);
        displayArea.getChildren().clear();
        displayArea.getChildren().add(chart);
    }

}