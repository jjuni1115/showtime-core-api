package com.showtime.coreapi.filter;

import com.showtime.coreapi.logger.CustomHttpResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class ResponseWrapperFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        CustomHttpResponseWrapper customHttpResponseWrapper = new CustomHttpResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(servletRequest, customHttpResponseWrapper);

        byte[] responseBody = customHttpResponseWrapper.getResponseBody();
        if(responseBody!=null && responseBody.length>0){
            servletResponse.getOutputStream().write(responseBody);
        }


    }
}
