package com.example.labb4fix2.View;

import com.example.labb4fix2.Model.WindowLevelProcessor;
import javafx.scene.image.Image;
/**
 * Handler for adjusting the window and level of an image.
 * This class uses the {@link WindowLevelProcessor} to perform the window-level adjustments.
 */
public class HandleWindowLevel {
    private final Image originalImage;
    private Image windowLevelImg;
    private final WindowLevelProcessor processor;
    /**
     * Constructs a HandleWindowLevel object with the specified image, window, and level values.
     *
     * @param originalImage The original image that needs its window and level adjusted.
     * @param window The window value to be used for adjustments.
     * @param level The level value to be used for adjustments.
     */
    public HandleWindowLevel(Image originalImage,int window, int level){
        this.originalImage = originalImage;
        this.processor = new WindowLevelProcessor(window,level);
    }

    /**
     * Adjusts the original image's window and level based on the specified values.
     * This method fetches the pixel matrix of the original image, processes it
     * to get the adjusted pixel matrix, and then converts it back to an image.
     */
    private void adjustWindowLevel(){
        int[][] originalImageData = ImageMatrixUtil.getPixelMatrixFromImage(originalImage);

        // Process the pixel matrix to get grayscale pixel matrix
        int[][] grayImageData = processor.processImage(originalImageData);

        // Convert grayscale pixel matrix back to Image
        windowLevelImg = ImageMatrixUtil.getImageFromPixelMatrix(grayImageData);
    }
    /**
     * Retrieves the window-level adjusted version of the original image.
     * If the adjusted image hasn't been generated yet, it will be generated first.
     *
     * @return Window-level adjusted version of the original image.
     */
    public Image getWindowLevelImg() {
        if (windowLevelImg == null){
            adjustWindowLevel();
        }
        return windowLevelImg;
    }
}