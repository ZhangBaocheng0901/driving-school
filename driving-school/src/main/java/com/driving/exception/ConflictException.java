// src/main/java/com/driving/exception/ConflictException.java
package com.driving.exception;

import org.springframework.http.HttpStatus;

/**
 * 资源冲突异常（HTTP 409）
 * 用于处理数据唯一性冲突等场景
 */
public class ConflictException extends CustomException {
    public ConflictException(String message) {
        super(HttpStatus.CONFLICT.value(), message);
    }
}