package com.devsu.accountmicroservice.infrastructure.rest.exception;

import com.devsu.library.infrastructure.rest.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, "InvalidRequest", exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String type, String message) {
        ErrorResponse response = new ErrorResponse(type, message, Instant.now(), status.value());
        return ResponseEntity.status(status).body(response);
    }
}
