package com.example.labb4fix2.Model;
/**
 * Processor for inverting the colors of an image.
 * Inversion is performed on each of the Red, Green, and Blue channels of a pixel.
 */
public class InvertColorsProcessor implements IProcessor {
    /**
     * Processes the given image and returns its color-inverted version.
     * For each pixel in the image, the Red, Green, and Blue channel values
     * are inverted (subtracted from 255) to produce the inverted color.
     *
     * @return A 2D array representing the color-inverted image, where each entry is
     *         an ARGB value of the pixel.
     */
    @Override
    public int[][] processImage(int[][] originalImg) {
        if (originalImg == null || originalImg.length == 0) {
            return null;
        }

        int width = originalImg.length;
        int height = originalImg[0].length;
        int[][] invertedImg = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int argb = originalImg[x][y];

                int a = (argb >> 24) & 0xFF;
                int r = 255 - ((argb >> 16) & 0xFF);
                int g = 255 - ((argb >> 8) & 0xFF);
                int b = 255 - (argb & 0xFF);

                invertedImg[x][y] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }
        return invertedImg;
    }
}