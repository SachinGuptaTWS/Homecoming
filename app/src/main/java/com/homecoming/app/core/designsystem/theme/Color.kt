package com.homecoming.app.core.designsystem.theme

import androidx.compose.ui.graphics.Color

internal val PurpleDeep = Color(0xFF5B2C6F)
internal val PurpleLight = Color(0xFF8E44AD)
internal val GoldAccent = Color(0xFFF4D03F)
internal val GoldMuted = Color(0xFFD4AC0D)
internal val CreamBackground = Color(0xFFFDF8F3)
internal val OnPurple = Color(0xFFFFFFFF)

/** Edge-to-edge scrims used by [com.homecoming.app.MainActivity]. */
object HomeComingSystemBar {
    val lightScrim: Color = CreamBackground
    val darkScrim: Color = PurpleDeep
}
