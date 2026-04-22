package com.simonchila.halo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_stats")
data class PlayerStats(
    @PrimaryKey val gamerTag: String,
    val rank: String,
    val kills: Int,
    val deaths: Int
)