package com.homecoming.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final HomeComingBackendProperties properties;

    public WebConfig(HomeComingBackendProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = properties.corsAllowedOriginPatterns().toArray(String[]::new);
        registry.addMapping("/graphql")
                .allowedOriginPatterns(origins)
                .allowedMethods("POST", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
        registry.addMapping("/graphiql/**")
                .allowedOriginPatterns(origins)
                .allowedMethods("GET", "HEAD", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
