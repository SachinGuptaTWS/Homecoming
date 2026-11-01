package com.homecoming.app

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.homecoming.app.core.designsystem.theme.HomeComingTheme
import com.homecoming.app.core.navigation.HomeComingNavHost

@Composable
fun HomeComingApp() {
    HomeComingTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val ready = backStackEntry != null

        HomeComingNavHost(navController = navController)
        ReportDrawnWhen { ready }
    }
}
