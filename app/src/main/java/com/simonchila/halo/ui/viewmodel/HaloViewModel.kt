package com.simonchila.halo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simonchila.halo.data.local.entities.PlayerStats
import com.simonchila.halo.data.repository.HaloRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HaloViewModel(private val repository: HaloRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Track the gamertag the user is currently looking for
    private val _currentTag = MutableStateFlow("")
    val currentTag = _currentTag.asStateFlow()

    /**
     * Combined state for the UI.
     * This looks at the whole DB list and finds the one matching our search.
     */
    val playerStats: StateFlow<List<PlayerStats>> = repository.allStats
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Call this when the search button is pressed.
     */
    fun refreshStats(gamerTag: String) {
        // Don't search if the tag is blank
        if (gamerTag.isBlank()) return

        viewModelScope.launch {
            try {
                Log.d("HaloVM", "INITIATING_UPLINK: $gamerTag")

                // 1. Set the tag immediately so the UI knows which one to look for
                _currentTag.value = gamerTag

                // 2. Start loading
                _isLoading.value = true

                // 3. Hit the Repository (API -> Room)
                repository.refreshPlayerStats(gamerTag)

            } catch (e: Exception) {
                Log.e("HaloVM", "UPLINK_FAILURE: ${e.message}")
            } finally {
                // 4. Always stop loading, even on error
                _isLoading.value = false
            }
        }
    }

    /**
     * Helper to clear the local cache if things get messy
     */
//    fun clearDatabase() {
//        viewModelScope.launch {
//            repository.clearAllData() // Ensure this exists in your repo/dao
//        }
//    }
}