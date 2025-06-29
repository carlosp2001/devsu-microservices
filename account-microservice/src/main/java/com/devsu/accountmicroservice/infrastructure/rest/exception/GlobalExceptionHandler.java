package com.devsu.accountmicroservice.infrastructure.rest.exception;

import com.devsu.accountmicroservice.domain.exception.AccountNotFoundException;
import com.devsu.library.domain.exception.PeticionNotFoundException;
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

    @ExceptionHandler(PeticionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRequestNotFound(PeticionNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, "PeticionNotFound", exception.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, "AccountNotFound", exception.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String type, String message) {
        ErrorResponse response = new ErrorResponse(type, message, Instant.now(), status.value());
        return ResponseEntity.status(status).body(response);
    }
}
