package com.showtime.coreapi.type;

import org.springframework.http.HttpStatus;

import java.util.Map;

public interface ErrorCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();

    static ErrorCode fromCode(String code, Map<String, ? extends ErrorCode> codeMap) {
        return codeMap.get(code);
    }
}

