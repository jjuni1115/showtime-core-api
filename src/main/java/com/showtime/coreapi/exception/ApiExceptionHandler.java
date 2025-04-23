package com.showtime.coreapi.exception;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showtime.coreapi.feign.ResponseDto;
import com.showtime.coreapi.response.ApiResponse;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    protected ResponseEntity<ApiResponse<String>> handleCustomException(CustomRuntimeException e){

        return ResponseEntity.status(e.getErrorCode().getStatus()).body(ApiResponse.error(e.getErrorCode().getMessage(),"",e.getErrorCode().getCode()));

    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<ApiResponse<String>> handleFeignException(FeignException e) {


ObjectMapper objectMapper = new ObjectMapper();


        ResponseDto<String> response = null;
        try {
            response = objectMapper.readValue(e.contentUTF8(), new TypeReference<ResponseDto<String>>() {});
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        return ResponseEntity.status(e.status()).body(ApiResponse.error(response.getMessage(),response.getPath(),response.getMessage()));

    }


}
