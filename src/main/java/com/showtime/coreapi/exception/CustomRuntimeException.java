package com.showtime.coreapi.exception;



import com.showtime.coreapi.type.ErrorCode;
import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomRuntimeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
