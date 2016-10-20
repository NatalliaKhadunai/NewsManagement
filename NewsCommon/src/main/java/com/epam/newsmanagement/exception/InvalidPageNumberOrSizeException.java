package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 9/27/2016.
 */
public class InvalidPageNumberOrSizeException extends RuntimeException {
    public InvalidPageNumberOrSizeException() {
        super();
    }
    public InvalidPageNumberOrSizeException(String message) {
        super(message);
    }
}
