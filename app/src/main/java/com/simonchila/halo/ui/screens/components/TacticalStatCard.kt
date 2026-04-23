package com.simonchila.halo.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.UnscTextGray

@Composable
fun TacticalStatCard(label: String, title: String, value: String) {
    TacticalBox(modifier = Modifier.fillMaxWidth()) {
        Text(label, color = UnscCyan, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        Text(title, color = UnscTextGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(
            text = value,
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace
        )
    }
}
