package com.showtime.coreapi.response;


import com.showtime.coreapi.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {

    private Boolean success;
    private String message;
    private T data;
    private String path;
    private String errorCode;


    public static <T> ApiResponse<T> ok(T data,String path){
        return new ApiResponse<>(true,"",data,path,"");
    }

    public static <T> ApiResponse<T> error(String message,String path,String errorCode){
        return new ApiResponse<>(false,message,null,path,errorCode);
    }

    public static <T> ApiResponse<T> error(String path, ErrorCode errorCode){
        return new ApiResponse<>(false,errorCode.getMessage(),null,path,errorCode.getCode());
    }


}
