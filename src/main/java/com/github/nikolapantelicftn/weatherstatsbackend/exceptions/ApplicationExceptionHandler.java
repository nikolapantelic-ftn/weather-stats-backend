package com.github.nikolapantelicftn.weatherstatsbackend.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    String handleEntityNotFound(EntityNotFoundException e) {
        log.info("NOT_FOUND: {}", e.getMessage());
        log.trace(e.getMessage(), e);

        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityExistsException.class)
    String handleApplicationException(EntityExistsException e) {
        log.info("BAD_REQUEST: {}", e.getMessage());
        log.trace(e.getMessage(), e);

        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    String handleExceptionDefault(RuntimeException e) {
        log.error(e.getMessage(), e);
        return "Internal server error has occurred!";
    }

}
