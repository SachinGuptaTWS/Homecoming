package com.homecoming.backend;

import com.homecoming.backend.config.HomeComingBackendProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(HomeComingBackendProperties.class)
public class HomeComingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeComingBackendApplication.class, args);
    }
}
