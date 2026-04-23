package com.simonchila.halo.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simonchila.halo.ui.screens.components.TacticalBox
import com.simonchila.halo.ui.theme.UnscBlueDark
import com.simonchila.halo.ui.theme.UnscCyan
import com.simonchila.halo.ui.theme.unscGridBackground

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.fillMaxSize().unscGridBackground().background(UnscBlueDark).padding(20.dp)) {
        Text("[001] TERMINAL CONFIGURATIONS", color = UnscCyan, fontSize = 18.sp)

        Spacer(Modifier.height(24.dp))

        TacticalBox(modifier = Modifier.fillMaxWidth()) {
            Text("HALO_API_KEY", color = UnscCyan, fontSize = 12.sp)
            Box(Modifier.fillMaxWidth().height(40.dp).background(Color.Black).padding(8.dp)) {
                Text("••••••••••••••••••••••••", color = Color.White)
            }
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = UnscCyan)
            ) {
                Text("SAVE_TO_DATASTORE", color = Color.Black)
            }
        }

        Spacer(Modifier.height(16.dp))

        TacticalBox(modifier = Modifier.fillMaxWidth()) {
            Text("CACHE_MANAGEMENT", color = UnscCyan, fontSize = 12.sp)
            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, Color.Red)
            ) {
                Text("PURGE_LOCAL_DATA", color = Color.Red)
            }
        }
    }
}