package com.gatech.cs6440.alcoholrecovery.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { Exception.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        ex.printStackTrace();;
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }



    @ExceptionHandler(value
            = {ResponseStatusException.class})
    protected ResponseEntity<Object> handleConflict2(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        ex.printStackTrace();
        ResponseStatusException rex = (ResponseStatusException)ex;
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), ((ResponseStatusException) ex).getStatus(), request);
    }
}