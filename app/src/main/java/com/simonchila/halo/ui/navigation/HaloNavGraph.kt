package com.simonchila.halo.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.simonchila.halo.ui.screens.HaloDashboard
import com.simonchila.halo.ui.screens.IntelScreen
import com.simonchila.halo.ui.screens.PlayerDetailScreen
import com.simonchila.halo.ui.screens.ProfileScreen
import com.simonchila.halo.ui.screens.SettingsScreen
import com.simonchila.halo.ui.screens.components.UnscBottomBar
import com.simonchila.halo.ui.viewmodel.HaloViewModel

@Composable
fun HaloNavGraph(viewModel: HaloViewModel) {
    val navController = rememberNavController()
    val statsList by viewModel.playerStats.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = { UnscBottomBar(
            navController,
            getIconForRoute = { route -> getIconForRoute(route) }
        ) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            // HOME (Dashboard)
            composable("home") {
                HaloDashboard(
                    viewModel = viewModel,
                    onPlayerClick = { tag ->
                        navController.navigate("detail/$tag")
                    }
                )
            }

            // INTEL (Match History - Image 3/5)
            composable("intel") {
                IntelScreen(viewModel)
            }

            // PROFILE (Progression - Image 4)
            composable("profile") {
                ProfileScreen(viewModel)
            }

            // SETTINGS (Terminal Config - Image 6)
            composable("settings") {
                SettingsScreen()
            }

            // DETAIL (Deep Dive)
            composable(
                route = "detail/{tag}",
                arguments = listOf(navArgument("tag") { type = NavType.StringType })
            ) { backStackEntry ->
                val tag = backStackEntry.arguments?.getString("tag")
                val player = statsList.find { it.gamerTag == tag }
                player?.let {
                    PlayerDetailScreen(stats = it, onBack = { navController.popBackStack() })
                }
            }
        }
    }
}

fun getIconForRoute(route: String?): ImageVector {
    return when (route) {
        "home" -> Icons.Default.Home
        "profile" -> Icons.Default.AccountCircle
        "intel" -> Icons.Default.Assessment // Looks like a tactical graph/chart
        "settings" -> Icons.Default.Settings
        else -> Icons.Default.Home
    }
}