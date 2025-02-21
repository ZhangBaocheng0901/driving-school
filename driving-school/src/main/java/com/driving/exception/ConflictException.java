
package com.driving.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends RuntimeException {

    private final HttpStatus statusCode;

    public ConflictException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}