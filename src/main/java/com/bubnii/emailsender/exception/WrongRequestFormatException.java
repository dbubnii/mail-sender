package com.bubnii.emailsender.exception;

public class WrongRequestFormatException extends RuntimeException {
    public WrongRequestFormatException(String message) {
        super(message);
    }
}
