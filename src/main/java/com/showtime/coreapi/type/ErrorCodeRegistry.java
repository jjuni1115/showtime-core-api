package com.showtime.coreapi.type;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeRegistry {
    private static final Map<String, ErrorCode> errorCodeMap = new HashMap<>();

    public static void registerErrorCode(ErrorCode errorCode) {
        errorCodeMap.put(errorCode.getCode(), errorCode);
    }

    public static ErrorCode getErrorCode(String code) {
        return errorCodeMap.get(code);
    }
}

