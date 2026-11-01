package com.homecoming.app.core.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Event(
    val id: String,
    val title: String,
    val scheduleLabel: String,
    val location: String,
)
