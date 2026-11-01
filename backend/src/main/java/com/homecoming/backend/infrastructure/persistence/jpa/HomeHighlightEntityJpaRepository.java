package com.homecoming.backend.infrastructure.persistence.jpa;

import com.homecoming.backend.infrastructure.persistence.jpa.entity.HomeHighlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface HomeHighlightEntityJpaRepository extends JpaRepository<HomeHighlightEntity, Short> {}
