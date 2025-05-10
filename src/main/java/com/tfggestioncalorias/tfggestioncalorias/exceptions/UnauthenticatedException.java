package com.tfggestioncalorias.tfggestioncalorias.exceptions;

public class UnauthenticatedException extends ApiException {
    public UnauthenticatedException(String errorValue, String message) {
        super( errorValue ,message);
    }
}
