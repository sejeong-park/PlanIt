package com.trip.planit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // REST API 경로
                .allowedOrigins("http://localhost:5173"); // Client 측 주소
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }
}
