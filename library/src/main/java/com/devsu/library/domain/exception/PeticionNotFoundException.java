package com.devsu.library.domain.exception;

public class PeticionNotFoundException extends RuntimeException{
    public PeticionNotFoundException(String message) {
        super(message);
    }
}
