package com.tfggestioncalorias.tfggestioncalorias.exceptions;

//Mensaje de si existe o no un valor
public class ExistsException extends ApiException {
    public ExistsException(String errorValue , String message) {
        super(errorValue, message); }
    }