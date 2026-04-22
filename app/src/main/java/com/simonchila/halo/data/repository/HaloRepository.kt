package com.simonchila.halo.data.repository

import android.util.Log
import com.simonchila.halo.data.local.dao.HaloDao
import com.simonchila.halo.data.local.entities.PlayerStats
import com.simonchila.halo.data.remote.api.HaloApiService
import com.simonchila.halo.data.remote.dto.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HaloRepository(
    private val apiService: HaloApiService,
    private val haloDao: HaloDao
) {
    // This Flow updates the UI automatically whenever the DB changes
    val allStats: Flow<List<PlayerStats>> = haloDao.getAllStats()

    suspend fun refreshPlayerStats(gamerTag: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getStats(gamerTag)
                if (response.isSuccessful) {
                    val dto = response.body()
                    // Convert DTOs to Entities and save to Room
                    dto?.results?.map { it.toEntity() }?.forEach { entity ->
                        haloDao.savePlayerStats(entity)
                    }
                }
            } catch (e: Exception) {
                // Log error - the UI will still show the old data from Room!
                Log.e("HaloRepository", "Log error - the UI will still show the old data from Room!")
            }
        }
    }
}