package com.simonchila.halo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simonchila.halo.ui.screens.components.TacticalBox
import com.simonchila.halo.ui.theme.UnscBlueDark
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.unscGridBackground
import com.simonchila.halo.ui.viewmodel.HaloViewModel

@Composable
fun IntelScreen(viewModel: HaloViewModel) {
    Column(modifier = Modifier.fillMaxSize().unscGridBackground().background(UnscBlueDark).padding(16.dp)) {
        Text("PERSONNEL_MATCH_HISTORY", color = UnscCyan, fontFamily = FontFamily.Monospace)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(10) { index -> // Placeholder for real match data
                MatchLogCard(isVictory = index % 2 == 0)
            }
        }
    }
}

@Composable
fun MatchLogCard(isVictory: Boolean) {
    TacticalBox(modifier = Modifier.fillMaxWidth().height(100.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(80.dp).background(Color.DarkGray)) // Map Image Placeholder
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = if (isVictory) "MISSION SUCCESS" else "MISSION FAILURE",
                    color = if (isVictory) Color.Green else Color.Red,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text("K/D: 2.4  ", color = UnscCyan, fontSize = 12.sp)
                    Text("KILLS: 18", color = UnscCyan, fontSize = 12.sp)
                }
            }
        }
    }
}