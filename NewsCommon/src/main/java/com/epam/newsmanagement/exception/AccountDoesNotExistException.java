package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 9/1/2016.
 */
public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException() {
        super();
    }
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
