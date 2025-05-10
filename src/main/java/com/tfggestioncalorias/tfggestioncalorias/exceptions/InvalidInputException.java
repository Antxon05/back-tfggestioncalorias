package com.tfggestioncalorias.tfggestioncalorias.exceptions;

public class InvalidInputException extends ApiException {
    public InvalidInputException(String errorValue, String message) {
        super(errorValue, message);
    }
}