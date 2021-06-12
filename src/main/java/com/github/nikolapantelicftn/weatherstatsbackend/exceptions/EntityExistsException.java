package com.github.nikolapantelicftn.weatherstatsbackend.exceptions;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String message) {
        super(message);
    }

}
