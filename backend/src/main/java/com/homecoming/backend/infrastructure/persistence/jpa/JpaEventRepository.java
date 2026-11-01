package com.homecoming.backend.infrastructure.persistence.jpa;

import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.event.EventRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
class JpaEventRepository implements EventRepository {

    private final EventEntityJpaRepository eventEntityJpaRepository;

    JpaEventRepository(EventEntityJpaRepository eventEntityJpaRepository) {
        this.eventEntityJpaRepository = eventEntityJpaRepository;
    }

    @Override
    public List<Event> findAllOrderById() {
        return eventEntityJpaRepository.findAllByOrderByIdAsc().stream()
                .map(SchedulePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Event> findById(String id) {
        return eventEntityJpaRepository.findById(id).map(SchedulePersistenceMapper::toDomain);
    }
}
