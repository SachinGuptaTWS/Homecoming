package com.homecoming.app.feature.events

import androidx.compose.runtime.Immutable
import com.homecoming.app.core.data.model.Event

@Immutable
data class EventsUiState(
    val events: List<Event> = emptyList(),
)
