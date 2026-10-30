package com.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidateException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors =new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidInput(
            IllegalArgumentException exception) {

        Map<String, String> errors =new HashMap<>();

        errors.put("message", exception.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(
            NewsNotFoundException exception) {

        Map<String, String> errors = new HashMap<>();

        errors.put("message", exception.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }
}
