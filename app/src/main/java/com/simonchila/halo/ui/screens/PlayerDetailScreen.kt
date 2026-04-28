package com.simonchila.halo.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.simonchila.halo.data.local.entities.PlayerStats
import com.simonchila.halo.ui.screens.components.TacticalBox
import com.simonchila.halo.ui.theme.UnscBlueDark
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.UnscTextGray
import com.simonchila.halo.ui.theme.unscGridBackground

@Composable
fun PlayerDetailScreen(stats: PlayerStats, onBack: () -> Unit, imageLoader: ImageLoader) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .unscGridBackground()
            .background(UnscBlueDark)
            .padding(16.dp)
    ) {
        // Back Button with Tactical Styling
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = UnscCyan)
        }

        Text(
            text = "MISSION TELEMETRY: ${stats.gamerTag.uppercase()}",
            style = MaterialTheme.typography.headlineMedium,
            color = UnscCyan,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Combat Stats Row
        TacticalBox(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("TEAM BLUE", color = UnscCyan, fontSize = 12.sp)
                    Text("50", color = UnscCyan, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                }
                Text("VS", color = UnscTextGray, fontWeight = FontWeight.Bold)
                Column(horizontalAlignment = Alignment.End) {
                    Text("TEAM RED", color = Color(0xFFFF5252), fontSize = 12.sp)
                    Text("32", color = Color(0xFFFF5252), fontSize = 48.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Large Detail Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            item { TacticalDetailItem("TOTAL KILLS", stats.kills.toString(), "[001]") }
            item { TacticalDetailItem("TOTAL DEATHS", stats.deaths.toString(), "[002]") }
            item { TacticalDetailItem("GAMES WON", stats.wins.toString(), "[003]") }
            item { TacticalDetailItem("GAMES LOST", stats.losses.toString(), "[004]") }
        }

        // Bottom Warning / Status Bar
        Surface(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            color = Color(0xFF421515),
            border = BorderStroke(1.dp, Color.Red)
        ) {
            Text(
                "⚠️ SHIELD FAILURE: 12 DETECTED // SECTOR 7 CLEAR",
                color = Color.Red,
                fontSize = 10.sp,
                modifier = Modifier.padding(8.dp),
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun TacticalDetailItem(label: String, value: String, code: String) {
    TacticalBox(modifier = Modifier.fillMaxWidth()) {
        Text(code, color = UnscCyan, fontSize = 8.sp)
        Text(label, color = UnscTextGray, fontSize = 10.sp)
        Text(value, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
    }
}