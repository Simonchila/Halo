package com.simonchila.halo.ui.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.simonchila.halo.ui.screens.HaloDashboard
import com.simonchila.halo.ui.screens.PlayerDetailScreen
import com.simonchila.halo.ui.viewmodel.HaloViewModel

@Composable
fun HaloNavGraph(viewModel: HaloViewModel) {
    val navController = rememberNavController()
    val statsList by viewModel.playerStats.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = "dashboard") {

        // Dashboard Route
        composable("dashboard") {
            HaloDashboard(
                viewModel = viewModel,
                onPlayerClick = { tag ->
                    navController.navigate("detail/$tag")
                }
            )
        }

        // Detail Route
        composable(
            route = "detail/{tag}",
            arguments = listOf(navArgument("tag") { type = NavType.StringType })
        ) { backStackEntry ->
            val tag = backStackEntry.arguments?.getString("tag")
            // Find the specific player from our local Room list
            val player = statsList.find { it.gamerTag == tag }

            player?.let {
                PlayerDetailScreen(stats = it, onBack = { navController.popBackStack() })
            }
        }
    }
}