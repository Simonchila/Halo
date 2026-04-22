package com.simonchila.halo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.simonchila.halo.data.local.entities.PlayerStats
import com.simonchila.halo.ui.screens.components.StatLabel
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@Composable
fun PlayerDetailScreen(stats: PlayerStats, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Back Button
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }

        // 🔹 Header Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .border(2.dp, Color(0xFF00E5FF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stats.rank.replace("Level ", ""), // Cleaner look
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = stats.gamerTag, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text(text = "Player Overview", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }

        // 🔹 Highlight Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF111827)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatLabel("Rank", stats.rank)
                StatLabel("Kills", stats.kills.toString())
                StatLabel("Deaths", stats.deaths.toString())
            }
        }

        // 🔹 Secondary Stats
        Row(Modifier.fillMaxWidth()) {
            StatInfoBox("Wins", stats.wins.toString(), Modifier.weight(1f))
            Spacer(modifier = Modifier.width(12.dp))
            StatInfoBox("Losses", stats.losses.toString(), Modifier.weight(1f))
        }
    }
}

@Composable
fun StatInfoBox(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F2937))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(value, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Text(title, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}