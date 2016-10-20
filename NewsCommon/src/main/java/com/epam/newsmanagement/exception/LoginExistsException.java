package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/26/2016.
 */
public class LoginExistsException extends RuntimeException {
    public LoginExistsException() {
        super();
    }
    public LoginExistsException(String message) {
        super(message);
    }
}
