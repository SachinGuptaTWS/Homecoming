package com.homecoming.app.feature.events

import androidx.compose.runtime.Immutable
import com.homecoming.app.core.model.Event

@Immutable
data class EventsUiState(
    val events: List<Event> = emptyList(),
)
