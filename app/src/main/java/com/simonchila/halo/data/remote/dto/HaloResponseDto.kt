package com.simonchila.halo.data.remote.dto

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.simonchila.halo.data.local.entities.PlayerStats

data class HaloResponseDto(
    @SerializedName("Results") val results: List<PlayerResultDto>?
) {
    // If the API returns a single object instead of a list,
    // we provide a way to map the first result found.
    fun toEntity(): PlayerStats? {
        return results?.firstOrNull()?.toEntity()
    }
}

data class PlayerResultDto(
    @SerializedName("Id") val gamerTag: String,
    @SerializedName("Result") val statsDetail: StatsDetailDto
) {
    // In PlayerResultDto.kt
    // In PlayerResultDto.kt
    fun toEntity(): PlayerStats {
        // 1. Encode the tag safely
        val encodedTag = java.net.URLEncoder.encode(this.gamerTag, "UTF-8")
            .replace("+", "%20")

        // Change /render to /emblem to test the connection
        val finalUrl = "https://www.haloapi.com/stats/h5/spartans/$encodedTag/emblem?size=256"
        Log.d("UPLINK_PATH", "Generated URL: $finalUrl")

        return PlayerStats(
            gamerTag = this.gamerTag,
            kills = statsDetail.kills ?: 0,
            deaths = statsDetail.deaths ?: 0,
            wins = statsDetail.wins ?: 0,
            losses = statsDetail.losses ?: 0,
            rank = statsDetail.rank?.toString() ?: "RECRUIT",
            imageUrl = "https://s3publicapis.azure-api.net/profile/h5/profiles/$encodedTag/emblem?size=256",
            serviceTag = gamerTag.take(4).uppercase()
        )
    }
}

data class StatsDetailDto(
    @SerializedName("TotalKills") val kills: Int?,
    @SerializedName("TotalDeaths") val deaths: Int?,
    @SerializedName("SpartanRank") val rank: Int?,
    @SerializedName("TotalGamesWon") val wins: Int?,
    @SerializedName("TotalGamesLost") val losses: Int?
)