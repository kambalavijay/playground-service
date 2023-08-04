package com.explore.playground.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    PGERR1000("Unexpected server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    private String errorDesc;
    private HttpStatus status;

    ErrorCode(String errorDesc, HttpStatus status) {
        this.errorDesc = errorDesc;
        this.status = status;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
