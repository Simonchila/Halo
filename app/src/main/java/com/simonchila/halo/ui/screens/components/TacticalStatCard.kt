package com.simonchila.halo.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.simonchila.halo.ui.theme.UnscCyan

@Composable
fun TacticalStatCard(
    index: String,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(1.dp, UnscCyan.copy(alpha = 0.3f))
            .background(Color.Black.copy(alpha = 0.2f))
            .padding(12.dp)
    ) {
        Text(
            text = index,
            color = UnscCyan.copy(alpha = 0.6f),
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace
        )

        Text(
            text = label,
            color = UnscCyan,
            style = MaterialTheme.typography.labelMedium,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = value,
                color = UnscCyan,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Monospace
            )

            // The "+0.02" delta indicator from the wireframe
            Text(
                text = " +0.02 Δ",
                color = Color(0xFF8BC34A),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // The Segmented Progress Bar from the wireframe
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(4) { i ->
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .weight(1f)
                        .background(if (i < 3) UnscCyan else UnscCyan.copy(alpha = 0.2f))
                )
            }
        }
    }
}
