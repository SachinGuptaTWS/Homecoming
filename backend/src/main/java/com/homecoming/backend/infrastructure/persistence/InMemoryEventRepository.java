package com.homecoming.backend.infrastructure.persistence;

import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.event.EventRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryEventRepository implements EventRepository {

    private final Map<String, Event> eventsById = new ConcurrentHashMap<>();

    InMemoryEventRepository() {
        for (Event event : DemoScheduleSeed.events()) {
            eventsById.put(event.id(), event);
        }
    }

    @Override
    public List<Event> findAllOrderById() {
        return eventsById.values().stream().sorted(Comparator.comparing(Event::id)).toList();
    }

    @Override
    public Optional<Event> findById(String id) {
        return Optional.ofNullable(eventsById.get(id));
    }
}
