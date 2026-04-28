package com.simonchila.halo.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.simonchila.halo.data.local.entities.PlayerStats
import kotlinx.coroutines.flow.Flow

@Dao
interface HaloDao {

    // Observes a single player by their tag
    @Query("SELECT * FROM player_stats WHERE gamerTag LIKE :tag")
    fun getPlayerStats(tag: String): Flow<PlayerStats?>

    // Observes the entire history of players you've searched for
    @Query("SELECT * FROM player_stats ORDER BY gamerTag ASC")
    fun getAllStats(): Flow<List<PlayerStats>>

    // Saves or updates the player data
    @Upsert
    suspend fun savePlayerStats(stats: PlayerStats)

    // Optional: Useful for clearing the cache
    @Query("DELETE FROM player_stats")
    suspend fun deleteAllStats()
}