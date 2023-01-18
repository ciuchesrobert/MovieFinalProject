package com.sparta.moviefinalproject.exceptionhandler.customexceptions;

public class KeyDoesNotExistException extends Exception {
    public KeyDoesNotExistException(String message) {
        super(message);
    }
}
