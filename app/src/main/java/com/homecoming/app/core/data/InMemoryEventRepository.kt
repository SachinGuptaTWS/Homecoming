package com.homecoming.app.core.data

import com.homecoming.app.core.data.source.EventLocalDataSource
import com.homecoming.app.core.model.Event
import com.homecoming.app.core.model.HomeHighlight
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Singleton
internal class InMemoryEventRepository @Inject constructor() : EventRepository {

    private val schedule = MutableStateFlow(EventLocalDataSource.schedule())
    private val highlight = MutableStateFlow(EventLocalDataSource.homeHighlight)

    override fun observeSchedule(): Flow<List<Event>> = schedule.asStateFlow()

    override fun observeHomeHighlight(): Flow<HomeHighlight> = highlight.asStateFlow()
}
