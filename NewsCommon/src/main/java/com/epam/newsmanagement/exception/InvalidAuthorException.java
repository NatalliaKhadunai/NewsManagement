package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class InvalidAuthorException extends RuntimeException {
    public InvalidAuthorException() {
        super();
    }
    public InvalidAuthorException(String message) {
        super(message);
    }
}
