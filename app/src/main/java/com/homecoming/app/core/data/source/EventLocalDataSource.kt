package com.homecoming.app.core.data.source

import com.homecoming.app.core.model.Event
import com.homecoming.app.core.model.HomeHighlight

internal object EventLocalDataSource {
    val homeHighlight = HomeHighlight(
        title = "Alumni Homecoming Weekend",
        dates = "Oct 12–14",
    )

    fun schedule(): List<Event> = listOf(
        Event("1", "Opening ceremony", "Fri · 6:00 PM", "Main quad"),
        Event("2", "Class of 2015 reunion", "Sat · 11:00 AM", "Student center"),
        Event("3", "Football game", "Sat · 3:00 PM", "Stadium"),
        Event("4", "Alumni brunch", "Sun · 10:00 AM", "Dining hall"),
    ).sortedBy { it.id }
}
