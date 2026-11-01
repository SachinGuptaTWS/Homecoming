package com.homecoming.app.core.model

data class HomeHighlight(
    val title: String,
    val dates: String,
) {
    val isConfigured: Boolean
        get() = title.isNotBlank() || dates.isNotBlank()

    companion object {
        val Empty = HomeHighlight(title = "", dates = "")
    }
}
