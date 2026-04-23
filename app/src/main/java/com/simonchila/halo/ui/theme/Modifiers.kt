package com.simonchila.halo.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.unscGridBackground() = drawBehind {
    val gridSize = 40.dp.toPx()
    val strokeWidth = 1.dp.toPx()
    val color = Color(0xFF1A1D21)

    // Vertical lines
    var x = 0f
    while (x < size.width) {
        drawLine(color, Offset(x, 0f), Offset(x, size.height), strokeWidth)
        x += gridSize
    }
    // Horizontal lines
    var y = 0f
    while (y < size.height) {
        drawLine(color, Offset(0f, y), Offset(size.width, y), strokeWidth)
        y += gridSize
    }
}