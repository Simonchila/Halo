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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simonchila.halo.ui.screens.components.TacticalBox
import com.simonchila.halo.ui.theme.UnscBlueDark
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.UnscTextGray
import com.simonchila.halo.ui.theme.unscGridBackground
import com.simonchila.halo.ui.viewmodel.HaloViewModel

@Composable
fun ProfileScreen(viewModel: HaloViewModel) {
    val stats by viewModel.playerStats.collectAsStateWithLifecycle()
    val player = stats.firstOrNull() // Focus on the primary user

    Column(
        modifier = Modifier
            .fillMaxSize()
            .unscGridBackground()
            .background(UnscBlueDark)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Header (Image 4 style)
        TacticalBox(modifier = Modifier.fillMaxWidth().height(140.dp)) {
            Text("ID_AUTHENTICATION: SPARTAN-ID-S117", color = UnscCyan, fontSize = 10.sp)
            Text(
                text = (player?.gamerTag ?: "MASTER_CHIEF").uppercase(),
                style = MaterialTheme.typography.displaySmall,
                color = UnscCyan,
                fontWeight = FontWeight.ExtraBold
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(color = UnscCyan, shape = RoundedCornerShape(2.dp)) {
                    Text(" RANK: NAVY_ADMIRAL ", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Surface(color = Color.Transparent, shape = RoundedCornerShape(2.dp), border = BorderStroke(1.dp, UnscCyan)) {
                    Text(" STATUS: ACTIVE_DUTY ", color = UnscCyan, fontSize = 10.sp)
                }
            }
        }

        // Spartan Progression
        TacticalBox(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, null, tint = UnscCyan, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                Text("SPARTAN PROGRESSION", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(16.dp))

            Text("XP_FLUX: 85,420 / 100,000", color = UnscCyan, fontSize = 11.sp)
            LinearProgressIndicator(
            progress = { 0.85f },
            modifier = Modifier.fillMaxWidth().height(12.dp).padding(vertical = 4.dp),
            color = UnscCyan,
            trackColor = UnscCyan.copy(alpha = 0.2f),
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
            Text("NEXT_RANK_UP: COMMANDER_V", color = UnscTextGray, fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        // Performance Overview Grid (2x2)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TacticalStatBox("ACCURACY_RATING", "48.2%", 0.48f, Modifier.weight(1f))
            TacticalStatBox("AVG_LIFE_SPAN", "02:15 M", 0.6f, Modifier.weight(1f))
        }
    }
}
@Composable
fun TacticalStatBox(label: String, value: String, progress: Float, modifier: Modifier = Modifier) {
    TacticalBox(modifier = modifier) {
        Text(label, color = UnscCyan, fontSize = 9.sp)
        Text(value, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier.fillMaxWidth().height(2.dp),
        color = UnscCyan,
        trackColor = Color.Transparent,
        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
    }
}