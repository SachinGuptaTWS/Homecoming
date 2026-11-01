package com.homecoming.app.feature.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homecoming.app.core.domain.ObserveScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class EventsViewModel @Inject constructor(
    observeSchedule: ObserveScheduleUseCase,
) : ViewModel() {

    val uiState: StateFlow<EventsUiState> =
        observeSchedule()
            .map(::EventsUiState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = EventsUiState(),
            )
}
