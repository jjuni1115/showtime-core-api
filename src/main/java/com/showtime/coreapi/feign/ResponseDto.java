package com.showtime.coreapi.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

    private boolean success;
    private String message;
    private T data;
    private String path;
    private String errorCode;

}
