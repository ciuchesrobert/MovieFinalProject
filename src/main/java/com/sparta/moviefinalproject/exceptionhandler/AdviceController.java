package com.sparta.moviefinalproject.exceptionhandler;

import com.sparta.moviefinalproject.exceptionhandler.customexceptions.KeyDoesNotExistException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import java.util.NoSuchElementException;


@RestControllerAdvice
@Log4j2
public class AdviceController {

    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceAccessException e){
        log.error(e.getMessage());
        return "<h1>Missing resource...</h1>";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotHandledException(NoHandlerFoundException e) {
        log.error(e.getMessage());
        return "<h1>Looks like something went wrong...</h1>";
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage());
        return "<h1>Looks like something is missing...</h1>";
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleHttpServerErrorException(HttpServerErrorException e) {
        log.error(e.getMessage());
        return "<h1>Server error...</h1>";
    }
    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleHttpClientErrorException(HttpClientErrorException e) {
        log.error(e.getMessage());
        return "<h1>Looks like we have an error...</h1>";
    }
    @ExceptionHandler(HttpStatusCodeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleHttpStatusCodeException(HttpStatusCodeException e) {
        log.error(e.getMessage());
        return "<h1>Something went wrong...</h1>";
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(HttpClientErrorException.NotFound e) {
        log.error(e.getMessage());
        return "<h1>We couldn't find what you were looking for...</h1>";
    }


    @ExceptionHandler(KeyDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodKeyDoesNotExistException(KeyDoesNotExistException e){
        log.error(e.getMessage());
        return "<h1>You provided an invalid key...</h1>";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return "<h1>Method argument is not valid...</h1>";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        return "<h1>Incorrect data used in request...</h1>";
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodNoSuchElementException(NoSuchElementException e) {
        log.error(e.getMessage());
        return "<h1>No such element in database...</h1>";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodIndexOutOfBoundsExceptions(IndexOutOfBoundsException e){
        log.error(e.getMessage());
        return "<h1>Index out of bounds...</h1>";
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleMethodExceptions(MethodNotAllowedException e){
        log.error(e.getMessage());
        return "<h1>Method not allowed...</h1>";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgsException(IllegalArgumentException e){
        log.error(e.getMessage());
        return "<h1>Argument type not allowed...</h1>";
    }

    @ExceptionHandler(TemplateInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgsException(TemplateInputException e){
        log.error(e.getMessage());
        return "<h1>Input type not allowed...</h1>";
    }
}