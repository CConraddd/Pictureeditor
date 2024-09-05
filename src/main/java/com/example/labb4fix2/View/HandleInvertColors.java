package com.example.labb4fix2.View;

import javafx.scene.image.Image;
import com.example.labb4fix2.Model.InvertColorsProcessor;
/**
 * Handler for converting a color image into its color-inverted version.
 * This class uses the {@link InvertColorsProcessor} to perform the color inversion.
 */
public class HandleInvertColors {
    private final Image originalImage;
    private Image invertedColorImg;
    private final InvertColorsProcessor processor;
    /**
     * Constructs a HandleInvertColors object with the specified image.
     *
     * @param originalImage The original image that needs to be color-inverted.
     */
    public HandleInvertColors(Image originalImage){
        this.originalImage = originalImage;
        this.processor = new InvertColorsProcessor();
    }

    /**
     * Converts the original image into its color-inverted equivalent.
     * This method fetches the pixel matrix of the original image, processes it
     * to get the color-inverted pixel matrix, and then converts it back to an image.
     */
    private void invertColorsImgConverter(){
        int[][] originalImageData = ImageMatrixUtil.getPixelMatrixFromImage(originalImage);

        int[][] invertedColorImgData = processor.processImage(originalImageData);

        invertedColorImg = ImageMatrixUtil.getImageFromPixelMatrix(invertedColorImgData);
    }
    /**
     * Retrieves the color-inverted version of the original image.
     * If the color-inverted image hasn't been generated yet, it will be generated first.
     *
     * @return Color-inverted version of the original image.
     */
    public Image getInvertedColorImg(){
        if(invertedColorImg == null){
            invertColorsImgConverter();
        }
        return invertedColorImg;
    }
}