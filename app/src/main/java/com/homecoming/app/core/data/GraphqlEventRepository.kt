package com.homecoming.app.core.data

import com.homecoming.app.core.data.network.ScheduleGraphQlApi
import com.homecoming.app.core.model.Event
import com.homecoming.app.core.model.HomeHighlight
import com.homecoming.app.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Singleton
internal class GraphqlEventRepository @Inject constructor(
    private val scheduleGraphQlApi: ScheduleGraphQlApi,
    @ApplicationScope private val applicationScope: CoroutineScope,
) : EventRepository {

    private val schedule = MutableStateFlow<List<Event>>(emptyList())
    private val highlight = MutableStateFlow(HomeHighlight.Empty)

    init {
        applicationScope.launch { refresh() }
    }

    override fun observeSchedule(): Flow<List<Event>> = schedule.asStateFlow()

    override fun observeHomeHighlight(): Flow<HomeHighlight> = highlight.asStateFlow()

    private suspend fun refresh() {
        scheduleGraphQlApi.fetchEvents()
            .onSuccess { schedule.value = it }
        scheduleGraphQlApi.fetchHomeHighlight()
            .onSuccess { fetched ->
                highlight.value = fetched ?: HomeHighlight.Empty
            }
    }
}
