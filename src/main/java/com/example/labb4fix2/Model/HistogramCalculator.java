package com.example.labb4fix2.Model;
/**
 * Processor for calculating the histogram of an image.
 * The histogram represents the distribution of pixel intensities for the
 * Red, Green, and Blue channels of an image.
 */
public class HistogramCalculator implements IProcessor {

    @Override
    public int[][] processImage(int[][] imageData) {
        // Initialize a 3x256 matrix to store histogram data.
        int[][] histogram = new int[3][256];

        for (int[] imageDatum : imageData) {
            for (int pixel : imageDatum) {
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                // Increment the histogram values for R, G, B
                histogram[0][red]++;
                histogram[1][green]++;
                histogram[2][blue]++;
            }
        }
        return histogram;
    }
}