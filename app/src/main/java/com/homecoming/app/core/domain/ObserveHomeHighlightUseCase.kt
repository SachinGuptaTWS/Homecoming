package com.homecoming.app.core.domain

import com.homecoming.app.core.data.EventRepository
import com.homecoming.app.core.model.HomeHighlight
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveHomeHighlightUseCase @Inject constructor(
    private val eventRepository: EventRepository,
) {
    operator fun invoke(): Flow<HomeHighlight> = eventRepository.observeHomeHighlight()
}
