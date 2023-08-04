package com.explore.playground.exceptions;

import com.explore.playground.error.ErrorCode;
import lombok.Data;

import java.util.Map;

@Data
public class PlayGroundException extends RuntimeException {

    private ErrorCode errorCode;
    private Map<String, Object> additionalInfo;
    private Throwable cause;

    public PlayGroundException(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public PlayGroundException(ErrorCode errorCode, Map<String, Object> additionalInfo) {
        this(errorCode, null, null);
    }

    public PlayGroundException(ErrorCode errorCode, Map<String, Object> additionalInfo, Throwable cause) {
        this.errorCode = errorCode;
        this.additionalInfo = additionalInfo;
        this.cause = cause;
    }
}
