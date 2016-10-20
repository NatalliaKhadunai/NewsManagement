package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class InvalidIdException extends RuntimeException {
    public InvalidIdException() {
        super();
    }
    public InvalidIdException(String message) {
        super(message);
    }
}
