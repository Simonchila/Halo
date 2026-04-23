package com.simonchila.halo.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val UnscColorScheme = darkColorScheme(
    primary = UnscCyan,
    secondary = UnscTextGray,
    tertiary = UnscWarningRed,
    background = UnscBlueDark,
    surface = UnscSurface,
    onPrimary = Color.Black, // Dark text on bright cyan buttons
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun HaloTheme(
    darkTheme: Boolean = true, // Default to true for that terminal look
    dynamicColor: Boolean = false, // Set to false to prevent Material You from breaking the Halo aesthetic
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // We generally want to ignore dynamic color to keep the UNSC branding
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> UnscColorScheme
    }

    val Typography = Typography(
        // For smaller data labels
        labelSmall = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp
        ),
        // For the actual stat numbers (Kills, Deaths)
        headlineMedium = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        ),
        // For general UI text
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Ensure your Typography.kt uses Monospace for that HUD feel
        content = content
    )
}