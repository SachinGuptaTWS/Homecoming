package com.homecoming.app.core.data

import com.homecoming.app.core.data.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class InMemoryEventRepository : EventRepository {

    private val schedule = MutableStateFlow(SampleContent.schedule())
    private val highlight = MutableStateFlow(SampleContent.homeHighlight)

    override fun observeSchedule(): Flow<List<Event>> = schedule.asStateFlow()

    override fun observeHomeHighlight(): Flow<HomeHighlight> = highlight.asStateFlow()
}
