package com.simonchila.halo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.simonchila.halo.data.local.HaloDatabase
import com.simonchila.halo.data.repository.HaloRepository
import com.simonchila.halo.di.RetrofitClient
import com.simonchila.halo.ui.navigation.HaloNavGraph
import com.simonchila.halo.ui.theme.HaloTheme
import com.simonchila.halo.ui.viewmodel.HaloViewModel
import com.simonchila.halo.ui.viewmodel.HaloViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Initialize DB and API
        val database = HaloDatabase.getDatabase(this)
        val apiService = RetrofitClient.createService()
        val imageLoader = RetrofitClient.createImageLoader(this)

        // 2. Initialize Repository
        val repository = HaloRepository(apiService, database.haloDao())

        // 3. Initialize ViewModel (Usually done via a Factory)
        val viewModelFactory = HaloViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HaloViewModel::class.java]

        setContent {
            HaloTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    HaloNavGraph(viewModel = viewModel, imageLoader = imageLoader)
                }
            }
        }
    }
}