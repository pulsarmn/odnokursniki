package org.odnokursniki.auth.exception;

public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException() {}

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
