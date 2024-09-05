package com.example.labb4fix2.View;


import javafx.scene.image.Image;
import com.example.labb4fix2.Model.GrayScaleProcessor;

public class HandleGrayScale {
    private final Image originalImage;
    private Image grayImage;
    private final GrayScaleProcessor processor;
    /**
     * Constructs a HandleGrayScale object with the specified image.
     *
     * @param originalImage The original image that needs to be converted to grayscale.
     */
    public HandleGrayScale(Image originalImage) {
        this.originalImage = originalImage;
        this.processor = new GrayScaleProcessor();
    }
    /**
     * Converts the original image into its grayscale equivalent.
     * This method fetches the pixel matrix of the original image, processes it
     * to get the grayscale pixel matrix, and then converts it back to an image.
     */
    private void convertToGrayscale() {
        // Convert originalImage to its pixel matrix form
        int[][] originalImageData = ImageMatrixUtil.getPixelMatrixFromImage(originalImage);

        // Process the pixel matrix to get grayscale pixel matrix
        int[][] grayImageData = processor.processImage(originalImageData);

        // Convert grayscale pixel matrix back to Image
        grayImage = ImageMatrixUtil.getImageFromPixelMatrix(grayImageData);

    }
    /**
     * Retrieves the grayscale version of the original image.
     * If the grayscale image hasn't been generated yet, it will be generated first.
     *
     * @return Grayscale version of the original image.
     */
    public Image getGrayscaleImage() {
        if (grayImage == null) {
            convertToGrayscale();
        }
        return grayImage;
    }
}