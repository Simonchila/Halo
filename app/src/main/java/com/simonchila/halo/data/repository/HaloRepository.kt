package com.simonchila.halo.data.repository

import android.R.attr.tag
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
                Log.d("HaloRepository", "Uplink Status: ${response.code()}")

                if (response.isSuccessful) {
                    val dto = response.body()

                    // Debug log the raw body to see the structure in Logcat
                    Log.d("HaloRepository", "Raw DTO: $dto")

                    // If results is null, it's likely a single-player response
                    // We handle both possibilities here
                    val entitiesToSave = dto?.results?.map { it.toEntity() }
                        ?: listOfNotNull(dto?.toEntity()) // Fallback to mapping the root object

                    if (entitiesToSave.isNotEmpty()) {
                        entitiesToSave.forEach { entity ->
                            haloDao.savePlayerStats(entity)
                            Log.d("HaloRepository", "Successfully stored: ${entity.gamerTag}")
                        }
                    } else {
                        Log.w("HaloRepository", "Uplink successful, but no tactical data parsed.")
                    }
                } else {
                    Log.e("HaloRepository", "Uplink Denied: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HaloRepository", "COMMS_BREAKDOWN: ${e.message}", e)
            }
        }
    }
}