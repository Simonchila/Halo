package com.simonchila.halo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simonchila.halo.data.local.entities.PlayerStats
import com.simonchila.halo.data.repository.HaloRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HaloViewModel(private val repository: HaloRepository) : ViewModel() {

    // Observe the database flow. UI will auto-update when this changes.
    val playerStats: StateFlow<List<PlayerStats>> = repository.allStats
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun refreshStats(gamerTag: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.refreshPlayerStats(gamerTag)
            _isLoading.value = false
        }
    }
}