package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class InvalidTagException extends RuntimeException {
    public InvalidTagException() {
        super();
    }
    public InvalidTagException(String message) {
        super(message);
    }
}
