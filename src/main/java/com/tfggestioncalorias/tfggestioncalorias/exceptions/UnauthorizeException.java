package com.tfggestioncalorias.tfggestioncalorias.exceptions;

//Excepción de no tiene autorización
public class UnauthorizeException extends ApiException {
    public UnauthorizeException(String errorValue, String message) {
        super(errorValue, message);
    }
}