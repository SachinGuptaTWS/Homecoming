package com.homecoming.app.feature.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiState(
    val featuredTitle: String = "",
    val featuredDates: String = "",
)
