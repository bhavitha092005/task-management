package com.bhavitha.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        String message = ex.getMessage().toLowerCase();

        if (message.contains("not found")) {
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        if (message.contains("forbidden")) {
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }

        if (message.contains("invalid")) {
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}