package com.sparta.moviefinalproject.exceptionhandler;

import com.sparta.moviefinalproject.exceptionhandler.customexceptions.KeyDoesNotExistException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
@Log4j2
public class AdviceController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage());
        return "<h1>Entity not found!</h1>";
    }

    @ExceptionHandler(KeyDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodKeyDoesNotExistException(KeyDoesNotExistException e){
        log.error(e.getMessage());
        return "<h1>You provided an invalid key</h1>";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return "<h1>Method argument is not valid</h1>";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        return "<h1>Incorrect data used in a Request</h1>";
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodNoSuchElementException(NoSuchElementException e) {
        log.error(e.getMessage());
        return "<h1>No such element in database</h1>";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodIndexOutOfBoundsExceptions(IndexOutOfBoundsException e){
        log.error(e.getMessage());
        return "<h1>Index out of bounds</h1>";
    }
}