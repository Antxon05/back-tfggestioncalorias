package com.tfggestioncalorias.tfggestioncalorias.exceptions;

import lombok.Getter;

@Getter
public abstract class ApiException extends RuntimeException {
    private final String errorValue;

    public ApiException(String errorValue, String message) {
        super(message);
        this.errorValue = errorValue;
    }

}
