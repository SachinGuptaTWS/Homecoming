package com.homecoming.backend.adapter.graphql;

import static org.mockito.BDDMockito.given;

import com.homecoming.backend.application.event.EventQueryService;
import com.homecoming.backend.application.highlight.HomeHighlightQueryService;
import com.homecoming.backend.common.error.GraphQlErrorAdvice;
import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.exception.ResourceNotFoundException;
import com.homecoming.backend.domain.highlight.HomeHighlight;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@GraphQlTest(ScheduleQueryController.class)
@Import(GraphQlErrorAdvice.class)
@ActiveProfiles("test")
class ScheduleQueryGraphQlTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private EventQueryService eventQueryService;

    @MockitoBean
    private HomeHighlightQueryService homeHighlightQueryService;

    @Test
    void eventsQuery_returnsSchedule() {
        given(eventQueryService.listSchedule())
                .willReturn(List.of(new Event("1", "Opening ceremony", "Fri · 6:00 PM", "Main quad")));

        graphQlTester
                .documentName("events")
                .execute()
                .path("events")
                .matchesJson(
                        """
                        [{"id":"1","title":"Opening ceremony","scheduleLabel":"Fri · 6:00 PM","location":"Main quad"}]
                        """);
    }

    @Test
    void homeHighlightQuery_returnsFeatured() {
        given(homeHighlightQueryService.getCurrent())
                .willReturn(new HomeHighlight("Alumni Homecoming Weekend", "Oct 12–14"));

        graphQlTester
                .documentName("homeHighlight")
                .execute()
                .path("homeHighlight.title")
                .entity(String.class)
                .isEqualTo("Alumni Homecoming Weekend");
    }

    @Test
    void eventQuery_whenMissing_returnsNotFoundError() {
        given(eventQueryService.getById("99")).willThrow(new ResourceNotFoundException("Event not found: 99"));

        graphQlTester
                .documentName("eventById")
                .variable("id", "99")
                .execute()
                .errors()
                .expect(error -> ErrorType.NOT_FOUND.equals(error.getErrorType()))
                .verify();
    }
}
