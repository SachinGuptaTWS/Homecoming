package com.homecoming.app.core.data

import com.homecoming.app.core.model.Event
import com.homecoming.app.core.model.HomeHighlight
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun observeSchedule(): Flow<List<Event>>
    fun observeHomeHighlight(): Flow<HomeHighlight>
}
