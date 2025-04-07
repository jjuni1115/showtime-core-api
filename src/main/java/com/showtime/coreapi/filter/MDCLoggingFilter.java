package com.showtime.coreapi.filter;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
@Order(PriorityOrdered.HIGHEST_PRECEDENCE)
public class MDCLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final UUID uuid = UUID.randomUUID();

        MDC.put("request_id",uuid.toString());

        filterChain.doFilter(servletRequest, servletResponse);

        MDC.clear();

    }
}
