package com.tfggestioncalorias.tfggestioncalorias.exceptions;

public class NotFoundException extends ApiException {
    public NotFoundException( String errorValue, String message) {

        super(errorValue, message);
    }
}