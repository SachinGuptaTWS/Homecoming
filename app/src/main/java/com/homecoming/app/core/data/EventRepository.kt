package com.homecoming.app.core.data

import com.homecoming.app.core.data.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun observeSchedule(): Flow<List<Event>>
    fun observeHomeHighlight(): Flow<HomeHighlight>

    companion object {
        val default: EventRepository = Default
    }
}

private object Default : EventRepository by InMemoryEventRepository()
