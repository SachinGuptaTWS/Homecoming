package com.homecoming.backend.adapter.graphql;

import com.homecoming.backend.application.event.EventQueryService;
import com.homecoming.backend.application.highlight.HomeHighlightQueryService;
import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.highlight.HomeHighlight;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public final class ScheduleQueryController {

    private final EventQueryService eventQueryService;
    private final HomeHighlightQueryService homeHighlightQueryService;

    public ScheduleQueryController(
            EventQueryService eventQueryService, HomeHighlightQueryService homeHighlightQueryService) {
        this.eventQueryService = eventQueryService;
        this.homeHighlightQueryService = homeHighlightQueryService;
    }

    @QueryMapping
    public List<Event> events() {
        return eventQueryService.listSchedule();
    }

    @QueryMapping
    public Event event(@Argument String id) {
        return eventQueryService.getById(id);
    }

    @QueryMapping
    public HomeHighlight homeHighlight() {
        return homeHighlightQueryService.getCurrent();
    }
}
