package com.tfggestioncalorias.tfggestioncalorias.exceptions;

import lombok.Getter;

//Clase padre de las otras, se encarga de definir que le pasamos
@Getter
public abstract class ApiException extends RuntimeException {
    private final String errorValue;

    public ApiException(String errorValue, String message) {
        super(message);
        this.errorValue = errorValue;
    }

}
