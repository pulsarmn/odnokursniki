package org.odnokursniki.auth.exception.handler;


import jakarta.servlet.http.HttpServletRequest;
import org.odnokursniki.auth.dto.ErrorResponse;
import org.odnokursniki.auth.exception.InvalidPhoneNumberException;
import org.odnokursniki.auth.exception.RateLimitingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPhoneNumberException(HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getRequestURI(), HttpStatus.BAD_REQUEST, "Invalid phone number");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(RateLimitingException.class)
    public ResponseEntity<ErrorResponse> handleRateLimitingException(HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getRequestURI(), HttpStatus.TOO_MANY_REQUESTS, "Too many requests");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
    }
}
