package com.homecoming.backend.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "homecoming.api")
public record HomeComingBackendProperties(List<String> corsAllowedOriginPatterns) {}
