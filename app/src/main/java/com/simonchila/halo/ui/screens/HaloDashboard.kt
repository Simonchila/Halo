package com.simonchila.halo.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import com.simonchila.halo.R
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import com.simonchila.halo.data.local.entities.PlayerStats
import com.simonchila.halo.ui.screens.components.HaloSearchBar
import com.simonchila.halo.ui.screens.components.TacticalStatCard
import com.simonchila.halo.ui.theme.UnscBlueDark
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.unscGridBackground
import com.simonchila.halo.ui.viewmodel.HaloViewModel
import coil.compose.AsyncImagePainter


@Composable
fun HaloDashboard(
    viewModel: HaloViewModel,
    onPlayerClick: (String) -> Unit,
    imageLoader: ImageLoader
) {
    val stats by viewModel.playerStats.collectAsStateWithLifecycle()
    val activeTag by viewModel.currentTag.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    // Logic to find the player that matches our current search
    val focusedPlayer = stats.find { it.gamerTag.equals(activeTag, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .unscGridBackground()
            .background(UnscBlueDark)
            .padding(16.dp)
    ) {
        // 1. HUD Header
        HUDHeader()

        // 2. Search Bar
        HaloSearchBar(onSearch = { viewModel.refreshStats(it) })

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Main Content Area
        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "SYNCHRONIZING_WITH_WAYPOINT...",
                        color = UnscCyan,
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            focusedPlayer != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 32.dp)
                ) {
                    // Identity Card - clickable to go to detailed view
                    item {
                        SpartanIdentityCard(
                            gamertag = focusedPlayer.gamerTag,
                            rank = focusedPlayer.rank,
                            imageUrl = focusedPlayer.imageUrl,    // Pass the new field
                            serviceTag = focusedPlayer.serviceTag, // Pass the new field
                            imageLoader = imageLoader,             // Pass the authenticated loader
                            modifier = Modifier.clickable {
                                onPlayerClick(focusedPlayer.gamerTag)
                            }
                        )
                    }

                    // Stats Grid Section
                    item {
                        StatsGridSection(focusedPlayer)
                    }

                    // Recent Deployments Section
                    item {
                        RecentDeploymentsSection()
                    }
                }
            }

            else -> {
                // Initial or Empty State
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = if (activeTag.isEmpty()) "AWAITING_INPUT..." else "NO_RECORDS_FOUND_FOR: $activeTag",
                        color = UnscCyan.copy(alpha = 0.5f),
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun HUDHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Side: Status Icon and Version
        Box(
            modifier = Modifier
                .size(32.dp)
                .border(1.dp, UnscCyan)
                .padding(4.dp)
        ) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
                tint = UnscCyan,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = " TACTICAL_HUD_V4.02",
            color = UnscCyan,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.weight(1f))

        // Right Side: Mission Clock
        Text(
            text = "MISSION_ELAPSED_04:12:05",
            color = UnscCyan,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace
        )
    }
}
@Composable
fun RecentDeploymentsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, UnscCyan.copy(alpha = 0.3f))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "RECENT_DEPLOYMENTS",
                color = UnscCyan,
                style = MaterialTheme.typography.labelMedium,
                fontFamily = FontFamily.Monospace
            )

            // "LIVE_FEED" tag from your wireframe
            Text(
                " LIVE_FEED ",
                color = Color.Black,
                modifier = Modifier.background(Color(0xFF8BC34A)), // Greenish tint
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sample Rows - You can eventually loop through player.recentMatches here
        DeploymentRow("VICTORY", "BEHEMOTH / SLAYER", "+15 MMR", UnscCyan)
        DeploymentRow("DEFEAT", "RECHARGE / STRONGHOLDS", "-08 MMR", Color.Red.copy(alpha = 0.7f))
        DeploymentRow("VICTORY", "BAZAAR / CTF", "+22 MMR", UnscCyan)
    }
}
@Composable
fun DeploymentRow(result: String, map: String, mmr: String, resultColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = result,
            color = resultColor,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.width(60.dp)
        )
        Text(
            text = map,
            color = UnscCyan.copy(alpha = 0.7f),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.weight(1f),
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = mmr,
            color = UnscCyan,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun SpartanIdentityCard(
    gamertag: String,
    imageUrl: String?,
    serviceTag: String,
    rank: String,
    imageLoader: ImageLoader, // MUST be passed here to use the API key
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, UnscCyan.copy(alpha = 0.3f))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Spartan Image Container
        Box(
            modifier = Modifier
                .size(200.dp)
                .border(1.dp, UnscCyan)
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = imageUrl,
                imageLoader = imageLoader, // Attach the authenticated loader
                contentDescription = "Spartan Armor Profile",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop, // Use Fit if the armor gets cut off
                error = painterResource(id = R.drawable.unsc_placeholder) // Optional fallback
            )


            // Service Tag Overlay (Dynamic from API)
            Text(
                text = serviceTag,
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Explicitly snap to bottom
                    .background(UnscCyan)
                    .padding(horizontal = 12.dp, vertical = 2.dp),
                color = UnscBlueDark,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace
            )
        }

        Text(
            text = "ID_IDENTIFICATION",
            color = UnscCyan.copy(alpha = 0.6f),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = gamertag.uppercase(),
            color = UnscCyan,
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.Monospace
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SPARTAN RANK",
                color = UnscCyan.copy(alpha = 0.6f),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = rank.uppercase(),
                color = UnscCyan,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun StatsGridSection(player: PlayerStats) {
    // Calculated logic
    val kdRatio = if (player.deaths > 0) player.kills.toFloat() / player.deaths else player.kills.toFloat()
    val totalMatches = player.wins + player.losses
    val winRate = if (totalMatches > 0) (player.wins.toFloat() / totalMatches * 100).toInt() else 0

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TacticalStatCard(
                "[001] PERFORMANCE_INDEX",
                "K/D RATIO",
                String.format("%.2f", kdRatio),
                modifier = Modifier.weight(1f)
            )
            TacticalStatCard(
                "[002] NEUTRALIZATIONS",
                "TOTAL KILLS",
                player.kills.toString(),
                modifier = Modifier.weight(1f)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TacticalStatCard(
                "[003] OPERATIONAL_SUCCESS",
                "WIN RATE",
                "$winRate%",
                modifier = Modifier.weight(1f)
            )
            TacticalStatCard(
                "[004] DEPLOYMENT_HISTORY",
                "MATCHES",
                totalMatches.toString(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
