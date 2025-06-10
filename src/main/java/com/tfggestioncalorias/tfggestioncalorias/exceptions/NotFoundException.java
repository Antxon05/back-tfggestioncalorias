package com.tfggestioncalorias.tfggestioncalorias.exceptions;

//Excepción de no se ha encontrado un valor
public class NotFoundException extends ApiException {
    public NotFoundException( String errorValue, String message) {
        super(errorValue, message);
    }
}