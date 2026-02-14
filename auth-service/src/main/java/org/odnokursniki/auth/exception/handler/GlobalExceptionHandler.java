package org.odnokursniki.auth.exception.handler;


import jakarta.servlet.http.HttpServletRequest;
import org.odnokursniki.auth.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getRequestURI(), HttpStatus.BAD_REQUEST, "Validation error");
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
