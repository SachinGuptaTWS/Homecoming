package com.homecoming.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homecoming.app.core.data.EventRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    repository: EventRepository = EventRepository.default,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        repository.observeHomeHighlight()
            .map { highlight ->
                HomeUiState(
                    featuredTitle = highlight.title,
                    featuredDates = highlight.dates,
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState(),
            )
}
