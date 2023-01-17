package com.sparta.moviefinalproject.exceptionhandler.customexceptions;

public class KeyDoesNotExistException extends RuntimeException {
    public KeyDoesNotExistException(String message) {
        super(message);
    }
}
