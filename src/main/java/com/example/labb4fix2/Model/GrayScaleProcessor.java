package com.example.labb4fix2.Model;

/**
 * Processor for converting an image into grayscale format.
 */
public class GrayScaleProcessor implements IProcessor {
    /**
     * Processes the given image and returns its grayscale version.
     *
     * @param originalImg The original image represented as a 2D array where each
     *                    entry is an ARGB value of the pixel.
     * @return A 2D array representing the grayscale image, where each entry is
     *         an ARGB value of the pixel.
     */
    @Override
    public int[][] processImage(int[][] originalImg) {
        if (originalImg == null || originalImg.length == 0) {
            return null; // Or handle it differently based on your requirements
        }

        int width = originalImg.length;
        int height = originalImg[0].length;
        int[][] grayImg = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int argb = originalImg[x][y];
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                // Standard formula for calculating luminance:
                int grayValue = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                // Repack the grayscale value into ARGB format
                grayImg[x][y] = (0xFF << 24) | (grayValue << 16) | (grayValue << 8) | grayValue;
            }
        }
        return grayImg;
    }
}