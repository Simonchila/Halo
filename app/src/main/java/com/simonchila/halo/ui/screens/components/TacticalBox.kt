package com.simonchila.halo.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.UnscSurface

@Composable
fun TacticalBox(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawBehind {
                val lineLength = 20.dp.toPx()
                val thickness = 2.dp.toPx()
                val color = UnscCyan

                // Top Left
                drawLine(color, Offset(0f, 0f), Offset(lineLength, 0f), thickness)
                drawLine(color, Offset(0f, 0f), Offset(0f, lineLength), thickness)

                // Top Right
                drawLine(color, Offset(size.width, 0f), Offset(size.width - lineLength, 0f), thickness)
                drawLine(color, Offset(size.width, 0f), Offset(size.width, lineLength), thickness)

                // Bottom Left & Right... (repeat logic for other corners)
            }
            .background(UnscSurface.copy(alpha = 0.8f))
            .padding(16.dp)
    ) {
        Column { content() }
    }
}
