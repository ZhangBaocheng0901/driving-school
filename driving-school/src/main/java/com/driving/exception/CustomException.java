
package com.driving.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    protected final HttpStatus statusCode;

    public CustomException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}