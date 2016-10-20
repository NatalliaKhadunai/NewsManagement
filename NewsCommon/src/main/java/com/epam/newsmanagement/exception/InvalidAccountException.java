package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class InvalidAccountException extends RuntimeException {
    public InvalidAccountException() {
        super();
    }
    public InvalidAccountException(String message) {
        super(message);
    }
}
