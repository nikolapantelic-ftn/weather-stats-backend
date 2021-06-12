package com.github.nikolapantelicftn.weatherstatsbackend.exceptions;

public class ApiException extends RuntimeException {

    public ApiException() {
        super("An error has occurred during communication with the weather API!");
    }

    public ApiException(String message) {
        super(message);
    }

}
