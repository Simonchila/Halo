package com.simonchila.halo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simonchila.halo.ui.screens.components.HaloSearchBar
import com.simonchila.halo.ui.screens.components.TacticalStatCard
import com.simonchila.halo.ui.theme.UnscBlueDark
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.unscGridBackground
import com.simonchila.halo.ui.viewmodel.HaloViewModel

@Composable
fun HaloDashboard(
    viewModel: HaloViewModel,
    onPlayerClick: (String) -> Unit) {
    val stats by viewModel.playerStats.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .unscGridBackground() // Our custom grid modifier
            .background(UnscBlueDark)
            .padding(16.dp)
    ) {
        // Top HUD Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Person, "Profile", tint = UnscCyan)
            Text(
                " TACTICAL_HUD_V4.02",
                color = UnscCyan,
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(Modifier.weight(1f))
            Text("MISSION_ELAPSED: 04:12:05", color = UnscCyan, style = MaterialTheme.typography.labelSmall)
        }


        HaloSearchBar(onSearch = { viewModel.refreshStats(it) })

        // 2x2 Stats Grid
        if (stats.isNotEmpty()) {
            val player = stats.first() // Let's show the first search result as the main focus

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    TacticalStatCard("[001] PERFORMANCE_INDEX", "K/D RATIO", "1.85")
                }
                item {
                    TacticalStatCard("[002] NEUTRALIZATIONS", "TOTAL KILLS", player.kills.toString())
                }
                item {
                    TacticalStatCard("[003] OPERATIONAL_SUCCESS", "WIN RATE", "62%")
                }
                item {
                    TacticalStatCard("[004] DEPLOYMENT_HISTORY", "MATCHES", player.wins.toString())
                }
            }
        }
    }
}