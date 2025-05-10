package com.tfggestioncalorias.tfggestioncalorias.exceptions;

public class ConflictException extends ApiException {
    public ConflictException(String errorValue, String message) {
        super(errorValue, message);
    }
}
