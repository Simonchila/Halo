package com.simonchila.halo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simonchila.halo.ui.screens.components.StatCard
import com.simonchila.halo.ui.viewmodel.HaloViewModel

@Composable
fun HaloMainScreen(viewModel: HaloViewModel) {

    val statsList by viewModel.playerStats.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var textFieldState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        OutlinedTextField(
            value = textFieldState,
            onValueChange = { textFieldState = it },
            label = { Text("Enter GamerTag") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (textFieldState.isNotBlank()) {
                            viewModel.refreshStats(textFieldState.trim())
                        }
                    }
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        if (statsList.isEmpty() && !isLoading) {
            Text("No stats found. Search a gamer tag.")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(statsList) { stats ->
                StatCard(stats = stats)
            }
        }
    }
}