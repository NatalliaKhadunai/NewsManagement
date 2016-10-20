package com.epam.newsmanagement.exception;

/**
 * Created by Natallia_Khadunai on 8/26/2016.
 */
public class WrongLoginOrPasswordException extends RuntimeException {
    public WrongLoginOrPasswordException() {
        super();
    }
    public WrongLoginOrPasswordException(String message) {
        super(message);
    }
}
