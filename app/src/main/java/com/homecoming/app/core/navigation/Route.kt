package com.homecoming.app.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data object Events : Route

    @Serializable
    data object Profile : Route
}
