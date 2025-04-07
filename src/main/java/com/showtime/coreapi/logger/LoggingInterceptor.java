package com.showtime.coreapi.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        log.info("Request Mehtod: [{}] URL: [{}]",request.getMethod(),request.getRequestURI());
        if(request.getParameterNames().hasMoreElements()){
            log.info("Params: [{}]",getRequestParam(request));
        }

        CustomHttpRequestWrapper requestWrapper = (CustomHttpRequestWrapper) request;

        if(requestWrapper.getRequestBody()!=null) {
            String requestBody = new String(requestWrapper.getRequestBody());

            if (request != null && requestBody.length() > 0) {
                log.info("Request Body: [{}]", requestBody);
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("Response Status: [{}]",response.getStatus());
        byte[] responseBody = ((CustomHttpResponseWrapper) response).getResponseBody();

        if(responseBody!=null && responseBody.length>0){
            log.info("Response Body: [{}]", new String(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readValue(responseBody,Object.class))));
        }


        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    private Map<String,String> getRequestParam(HttpServletRequest request){

        return Collections.list(request.getParameterNames()).stream().collect(Collectors.toMap(
                paramName-> paramName, paramName -> request.getParameter(paramName)
        ));

    }

}
