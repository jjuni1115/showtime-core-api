package com.showtime.coreapi.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public AuthRequestInterceptor feignRequestInterceptor() {
        return new AuthRequestInterceptor();
    }


}
