package com.homecoming.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureGraphQlTester
@ActiveProfiles("test")
class ScheduleGraphQlIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void eventsQuery_whenDatabaseEmpty_returnsEmptyList() {
        graphQlTester
                .documentName("events")
                .execute()
                .path("events")
                .matchesJson("[]");
    }
}
