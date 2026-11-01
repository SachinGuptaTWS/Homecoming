package com.homecoming.app.core.domain

import com.homecoming.app.core.data.EventRepository
import com.homecoming.app.core.model.Event
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveScheduleUseCase @Inject constructor(
    private val eventRepository: EventRepository,
) {
    operator fun invoke(): Flow<List<Event>> = eventRepository.observeSchedule()
}
