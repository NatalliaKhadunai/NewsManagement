package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class InvalidArticleException extends RuntimeException {
    public InvalidArticleException() {
        super();
    }

    public InvalidArticleException(String message) {
        super(message);
    }
}
