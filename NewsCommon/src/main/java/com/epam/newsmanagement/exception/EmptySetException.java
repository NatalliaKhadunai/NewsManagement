package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class EmptySetException extends RuntimeException {
    public EmptySetException() {
        super();
    }
    public EmptySetException(String message) {
        super(message);
    }
}
