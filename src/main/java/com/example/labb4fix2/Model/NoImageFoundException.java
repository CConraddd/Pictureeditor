package com.example.labb4fix2.Model;
/**
 * Custom exception indicating that no image was found or loaded.
 * Typically thrown in scenarios where image processing or other operations
 * are attempted without a valid image being present.
 */
public class NoImageFoundException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the getMessage() method.
     */
    public NoImageFoundException(String message) {
        super(message);
    }
}