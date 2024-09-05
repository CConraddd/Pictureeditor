package com.example.labb4fix2.Model;
/**
 * Processor for adjusting the window and level of an image.
 */
public class WindowLevelProcessor implements IProcessor {
    private int window;
    private int level;
    /**
     * Constructs a WindowLevelProcessor with the given window and level values.
     *
     * @param window The window width for the adjustment.
     * @param level The midpoint intensity value around which the window is applied.
     */
    public WindowLevelProcessor(int window, int level) {
        this.window = window;
        this.level = level;
    }

    /**
     * Processes the given image and returns its window/level adjusted version.
     *
     * @param originalImg The original image represented as a 2D array where each
     *                    entry is an ARGB value of the pixel.
     * @return A 2D array representing the window/level adjusted image.
     */
    @Override
    public int[][] processImage(int[][] originalImg) {
        int width = originalImg.length;
        int height = originalImg[0].length;
        int[][] processedImg = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int argb = originalImg[x][y];
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                r = adjustPixelValueForWindowLevel(r, window, level);
                g = adjustPixelValueForWindowLevel(g, window, level);
                b = adjustPixelValueForWindowLevel(b, window, level);

                // Repack the pixel values into ARGB format
                processedImg[x][y] = (0xFF << 24) | (r << 16) | (g << 8) | b;
            }
        }
        return processedImg;
    }
    /**
     * Adjusts the pixel intensity value based on the specified window and level.
     *
     * @param value The original intensity value.
     * @param window The window width.
     * @param level The midpoint intensity.
     * @return Adjusted intensity value.
     */
    private int adjustPixelValueForWindowLevel(int value, int window, int level) {
        int halfWindow = window / 2;
        int minValue = level - halfWindow;
        int maxValue = level + halfWindow;

        if (value < minValue) {
            return 0;
        } else if (value > maxValue) {
            return 255;
        } else {
            // Scale the value within the window range
            return (int) (255.0 * (value - minValue) / window);
        }
    }
}