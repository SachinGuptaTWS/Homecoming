package com.homecoming.backend.infrastructure.persistence.jpa;

import com.homecoming.backend.infrastructure.persistence.jpa.entity.EventEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = EventEntity.class)
@EnableJpaRepositories(basePackages = "com.homecoming.backend.infrastructure.persistence.jpa")
public class JpaPersistenceConfig {}
