package com.example.labb4fix2.Model;
/**
 * Interface defining the structure for image processors.
 * Implementing classes are expected to provide a method to process
 * images represented as 2D arrays of ARGB values.
 */

interface IProcessor {
    int[][] processImage(int[][] originalImg);
}