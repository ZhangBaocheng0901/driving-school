
package com.driving.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends CustomException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Validation failed", HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}