package com.homecoming.backend.infrastructure.persistence.jpa;

import com.homecoming.backend.infrastructure.persistence.jpa.entity.EventEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface EventEntityJpaRepository extends JpaRepository<EventEntity, String> {

    List<EventEntity> findAllByOrderByIdAsc();
}
