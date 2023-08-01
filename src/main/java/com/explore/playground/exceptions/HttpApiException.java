package com.explore.playground.exceptions;

import lombok.Getter;

import java.util.Map;

public class HttpApiException extends RuntimeException {
    @Getter private int httpStatus;
    @Getter private Map<String, Object> errorInfo;

    public HttpApiException(String message) {
        super(message);
    }

    public HttpApiException(String message, Map<String, Object> errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public HttpApiException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
