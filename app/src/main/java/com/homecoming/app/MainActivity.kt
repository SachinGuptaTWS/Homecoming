package com.homecoming.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.homecoming.app.core.designsystem.theme.HomeComingSystemBar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = HomeComingSystemBar.lightScrim.toArgb(),
                darkScrim = HomeComingSystemBar.darkScrim.toArgb(),
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = HomeComingSystemBar.lightScrim.toArgb(),
                darkScrim = HomeComingSystemBar.darkScrim.toArgb(),
            ),
        )
        setContent { HomeComingApp() }
    }
}
