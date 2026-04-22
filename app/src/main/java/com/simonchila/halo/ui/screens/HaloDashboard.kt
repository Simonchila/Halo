package com.simonchila.halo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simonchila.halo.ui.screens.components.HaloPlayerCard
import com.simonchila.halo.ui.screens.components.HaloSearchBar
import com.simonchila.halo.ui.viewmodel.HaloViewModel

@Composable
fun HaloDashboard(viewModel: HaloViewModel, onPlayerClick: (String) -> Unit) {
    val statsList by viewModel.playerStats.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0B0E11))) {
        // App Header
        Text(
            text = "HALO TERMINAL",
            style = MaterialTheme.typography.displaySmall,
            color = Color(0xFF00E5FF), // Cyber Blue
            modifier = Modifier.padding(16.dp)
        )

        // Search Bar (Styled like a terminal input)
        HaloSearchBar(onSearch = { tag ->
            viewModel.refreshStats(tag)
        })

        Spacer(modifier = Modifier.height(20.dp))

        // Recent Activity Label
        Text(
            text = "SERVICE RECORDS",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyColumn(contentPadding = PaddingValues(16.dp)) {

            items(statsList) { stats ->
                HaloPlayerCard(stats = stats, onClick = { onPlayerClick(stats.gamerTag) })
            }
        }
    }
}