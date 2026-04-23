package com.simonchila.halo.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.simonchila.halo.ui.theme.UnscBlueDark
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.UnscTextGray

@Composable
fun UnscBottomBar(navController: NavController, getIconForRoute: (String) -> ImageVector) {
    val items = listOf("home", "profile", "intel", "settings")
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(UnscBlueDark)
            .border(1.dp, Color.DarkGray, RectangleShape),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { route ->
            val selected = currentRoute == route
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { navController.navigate(route) }
                    .border(0.5.dp, if (selected) UnscCyan else Color.Transparent)
                    .background(if (selected) UnscCyan.copy(alpha = 0.1f) else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = getIconForRoute(route),
                        contentDescription = route,
                        tint = if (selected) UnscCyan else UnscTextGray
                    )
                    Text(
                        text = route.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selected) UnscCyan else UnscTextGray,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}