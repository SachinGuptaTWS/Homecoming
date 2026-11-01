package com.homecoming.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homecoming.app.core.domain.ObserveHomeHighlightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    observeHomeHighlight: ObserveHomeHighlightUseCase,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        observeHomeHighlight()
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
