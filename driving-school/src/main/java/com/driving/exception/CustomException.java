// src/main/java/com/driving/exception/CustomException.java
package com.driving.exception;

/**
 * 自定义异常基类
 * 所有业务异常都应继承此类
 */
public class CustomException extends RuntimeException {
    private final int code;

    public CustomException(String message) {
        this(HttpStatus.BAD_REQUEST.value(), message);
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}