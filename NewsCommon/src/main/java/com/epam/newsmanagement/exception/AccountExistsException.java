package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 9/1/2016.
 */
public class AccountExistsException extends RuntimeException {
    public AccountExistsException() {
        super();
    }
    public AccountExistsException(String message) {
        super(message);
    }
}
