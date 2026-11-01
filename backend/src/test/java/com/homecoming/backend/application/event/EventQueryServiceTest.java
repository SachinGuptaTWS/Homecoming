package com.homecoming.backend.application.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.event.EventRepository;
import com.homecoming.backend.domain.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventQueryServiceTest {

    private EventQueryService service;

    @BeforeEach
    void setUp() {
        EventRepository repository = new EventRepository() {
            @Override
            public List<Event> findAllOrderById() {
                return List.of(new Event("2", "B", "Sat", "Hall"), new Event("1", "A", "Fri", "Quad"));
            }

            @Override
            public Optional<Event> findById(String id) {
                return id.equals("1") ? Optional.of(new Event("1", "A", "Fri", "Quad")) : Optional.empty();
            }
        };
        service = new EventQueryService(repository);
    }

    @Test
    void listSchedule_returnsDefensiveCopy() {
        assertThat(service.listSchedule()).extracting(Event::id).containsExactly("2", "1");
    }

    @Test
    void getById_whenMissing_throwsNotFound() {
        assertThatThrownBy(() -> service.getById("missing")).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void getById_whenBlank_throwsIllegalArgument() {
        assertThatThrownBy(() -> service.getById("  ")).isInstanceOf(IllegalArgumentException.class);
    }
}
