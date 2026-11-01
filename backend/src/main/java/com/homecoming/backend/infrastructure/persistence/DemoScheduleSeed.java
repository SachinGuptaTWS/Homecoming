package com.homecoming.backend.infrastructure.persistence;

import com.homecoming.backend.domain.event.Event;
import com.homecoming.backend.domain.highlight.HomeHighlight;
import java.util.List;

final class DemoScheduleSeed {

    private DemoScheduleSeed() {}

    static List<Event> events() {
        return List.of(
                new Event("1", "Opening ceremony", "Fri · 6:00 PM", "Main quad"),
                new Event("2", "Class of 2015 reunion", "Sat · 11:00 AM", "Student center"),
                new Event("3", "Football game", "Sat · 3:00 PM", "Stadium"),
                new Event("4", "Alumni brunch", "Sun · 10:00 AM", "Dining hall"));
    }

    static HomeHighlight homeHighlight() {
        return new HomeHighlight("Alumni Homecoming Weekend", "Oct 12–14");
    }
}
