package org.odnokursniki.auth.exception;

public class RateLimitingException extends RuntimeException {

    public RateLimitingException(String message) {
        super(message);
    }
}
