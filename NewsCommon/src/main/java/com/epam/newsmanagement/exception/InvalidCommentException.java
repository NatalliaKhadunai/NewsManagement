package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class InvalidCommentException extends RuntimeException {
    public InvalidCommentException() {
        super();
    }
    public InvalidCommentException(String message) {
        super(message);
    }
}
