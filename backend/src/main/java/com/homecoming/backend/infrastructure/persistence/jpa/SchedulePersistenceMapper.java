package com.homecoming.backend.infrastructure.persistence.jpa;

import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.highlight.HomeHighlight;
import com.homecoming.backend.infrastructure.persistence.jpa.entity.EventEntity;
import com.homecoming.backend.infrastructure.persistence.jpa.entity.HomeHighlightEntity;

final class SchedulePersistenceMapper {

    private SchedulePersistenceMapper() {}

    static Event toDomain(EventEntity entity) {
        return new Event(entity.getId(), entity.getTitle(), entity.getScheduleLabel(), entity.getLocation());
    }

    static HomeHighlight toDomain(HomeHighlightEntity entity) {
        return new HomeHighlight(entity.getTitle(), entity.getDates());
    }
}
