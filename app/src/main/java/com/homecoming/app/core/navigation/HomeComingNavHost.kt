package com.homecoming.app.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.homecoming.app.feature.events.EventsRoute
import com.homecoming.app.feature.home.HomeRoute
import com.homecoming.app.feature.profile.ProfileRoute

@Composable
fun HomeComingNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = { HomeComingBottomBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            composable<Route.Home> {
                HomeRoute(
                    onViewSchedule = { navController.navigateToTopLevel(Route.Events) },
                )
            }
            composable<Route.Events> {
                EventsRoute()
            }
            composable<Route.Profile> {
                ProfileRoute()
            }
        }
    }
}

@Composable
private fun HomeComingBottomBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        TopLevelDestination.entries.forEach { destination ->
            BottomNavItem(
                destination = destination,
                selected = backStackEntry?.destination?.hasRoute(destination.route::class) == true,
                navController = navController,
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    destination: TopLevelDestination,
    selected: Boolean,
    navController: NavHostController,
) {
    val label = stringResource(destination.labelRes)
    val onClick = remember(destination.route, selected) {
        {
            if (!selected) {
                navController.navigateToTopLevel(destination.route)
            }
        }
    }

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                contentDescription = label,
            )
        },
        label = { Text(label) },
    )
}
