package com.homecoming.backend.domain.event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    List<Event> findAllOrderById();

    Optional<Event> findById(String id);
}
