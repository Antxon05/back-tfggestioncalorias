package com.tfggestioncalorias.tfggestioncalorias.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Una serie de mensajes predefinidos
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", "bad_request",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleDuplicateKeyException(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", "duplicate_key",
                "message", "A record with this value already exists and must be unique."
        ));
    }

    @ExceptionHandler({NotFoundException.class, ExistsException.class, UnauthorizeException.class, InvalidInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleCustomExceptions(ApiException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", ex.getErrorValue(),
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleHandlerMethodValidation(HandlerMethodValidationException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", "validation_error",
                "message", "Invalid request parameter"
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", fieldError.getField());
            errorDetails.put("message", fieldError.getDefaultMessage());
            return errorDetails;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("error", "validation_error");
        response.put("message", "Invalid request parameter");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }
}

