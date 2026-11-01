package com.homecoming.app.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PurpleLight,
    onPrimary = OnPurple,
    primaryContainer = PurpleDeep,
    onPrimaryContainer = GoldAccent,
    secondary = GoldAccent,
    onSecondary = PurpleDeep,
    secondaryContainer = GoldMuted,
    onSecondaryContainer = PurpleDeep,
    background = Color(0xFF1A1020),
    onBackground = CreamBackground,
    surface = Color(0xFF241528),
    onSurface = CreamBackground,
    surfaceContainerHigh = Color(0xFF32203A),
)

private val LightColorScheme = lightColorScheme(
    primary = PurpleDeep,
    onPrimary = OnPurple,
    primaryContainer = PurpleLight,
    onPrimaryContainer = PurpleDeep,
    secondary = GoldMuted,
    onSecondary = PurpleDeep,
    secondaryContainer = GoldAccent,
    onSecondaryContainer = PurpleDeep,
    background = CreamBackground,
    onBackground = PurpleDeep,
    surface = CreamBackground,
    onSurface = PurpleDeep,
)

@Composable
fun HomeComingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val useDynamicColor = dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        useDynamicColor && darkTheme -> dynamicDarkColorScheme(context)
        useDynamicColor && !darkTheme -> dynamicLightColorScheme(context)
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = HomeComingShapes,
        typography = HomeComingTypography,
        content = content,
    )
}
