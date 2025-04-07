package com.showtime.coreapi.filter;

import com.showtime.coreapi.logger.CustomHttpRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.logging.LogRecord;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class RequestWrapperFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        CustomHttpRequestWrapper requestWrapper = new CustomHttpRequestWrapper(httpRequest);


        chain.doFilter(requestWrapper, response);
    }

}
