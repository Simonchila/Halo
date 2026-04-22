package com.simonchila.halo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simonchila.halo.data.repository.HaloRepository

class HaloViewModelFactory(private val repository: HaloRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HaloViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HaloViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}