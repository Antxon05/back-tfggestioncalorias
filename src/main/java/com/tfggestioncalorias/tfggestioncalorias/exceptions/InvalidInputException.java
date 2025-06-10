package com.tfggestioncalorias.tfggestioncalorias.exceptions;

//Excepción de valor inválido
public class InvalidInputException extends ApiException {
    public InvalidInputException(String errorValue, String message) {
        super(errorValue, message);
    }
}