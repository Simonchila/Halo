package com.simonchila.halo.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HaloResponseDto(
    @SerializedName("Results") val results: List<PlayerResultDto>
)

data class PlayerResultDto(
    @SerializedName("Id") val gamerTag: String,
    @SerializedName("Result") val statsDetail: StatsDetailDto
)

data class StatsDetailDto(
    @SerializedName("TotalKills") val kills: Int,
    @SerializedName("TotalDeaths") val deaths: Int,
    @SerializedName("SpartanRank") val rank: Int
)