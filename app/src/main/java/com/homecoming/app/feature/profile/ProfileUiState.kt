package com.homecoming.app.feature.profile

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileUiState(
    val displayName: String = "",
    val graduationYear: String = "",
)
