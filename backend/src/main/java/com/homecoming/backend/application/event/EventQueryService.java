package com.homecoming.backend.application.event;

import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.event.EventRepository;
import com.homecoming.backend.domain.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public final class EventQueryService {

    private final EventRepository eventRepository;

    public EventQueryService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> listSchedule() {
        return List.copyOf(eventRepository.findAllOrderById());
    }

    public Event getById(String id) {
        requireNonBlank(id, "Event id is required");
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + id));
    }

    private static void requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
}
