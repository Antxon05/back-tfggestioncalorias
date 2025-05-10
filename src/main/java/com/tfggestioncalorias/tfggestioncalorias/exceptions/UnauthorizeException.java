package com.tfggestioncalorias.tfggestioncalorias.exceptions;

public class UnauthorizeException extends ApiException {
    public UnauthorizeException(String errorValue, String message) {
        super(errorValue, message);
    }
}