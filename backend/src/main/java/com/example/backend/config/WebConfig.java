package com.example.backend.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override public void addCorsMappings(CorsRegistry r) {
        r.addMapping("/**")
                .allowedOrigins(System.getenv().getOrDefault("CORS_ORIGIN","http://localhost:5173"))
                .allowedMethods("GET","POST","PUT","DELETE","PATCH");
    }
}
