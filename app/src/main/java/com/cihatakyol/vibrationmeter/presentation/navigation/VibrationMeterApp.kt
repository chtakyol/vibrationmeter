package com.cihatakyol.vibrationmeter.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cihatakyol.vibrationmeter.presentation.ui.sessiondetailscreen.SessionDetailScreen
import com.cihatakyol.vibrationmeter.presentation.ui.sessionsscreen.SessionListScreen
import com.cihatakyol.vibrationmeter.presentation.ui.mainscreen.VibrationScreen

/**
 * Main app composable with bottom navigation.
 */
@Composable
fun VibrationMeterApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.LIVE,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationRoutes.LIVE) {
                VibrationScreen()
            }

            composable(NavigationRoutes.SESSIONS) {
                SessionListScreen(
                    onSessionClick = { sessionId ->
                        navController.navigate(NavigationRoutes.sessionDetail(sessionId))
                    }
                )
            }

            composable(NavigationRoutes.SESSION_DETAIL) {
                SessionDetailScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Only show bottom bar on main screens (not on detail screen)
    val showBottomBar = currentDestination?.route in listOf(
        NavigationRoutes.LIVE,
        NavigationRoutes.SESSIONS
    )

    if (showBottomBar) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp
        ) {
            bottomNavItems.forEach { item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                    },
                    label = { Text(item.label) },
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            // Pop up to the start destination to avoid building up a back stack
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

/**
 * Bottom navigation items.
 */
private val bottomNavItems = listOf(
    BottomNavItem(
        route = NavigationRoutes.LIVE,
        icon = Icons.Default.PlayArrow,
        label = "Live"
    ),
    BottomNavItem(
        route = NavigationRoutes.SESSIONS,
        icon = Icons.AutoMirrored.Filled.List,
        label = "Sessions"
    )
)

/**
 * Data class for bottom navigation items.
 */
private data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)
