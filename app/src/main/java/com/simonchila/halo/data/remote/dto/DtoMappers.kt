package com.simonchila.halo.data.remote.dto

import com.simonchila.halo.data.local.entities.PlayerStats

fun PlayerResultDto.toEntity(): PlayerStats {
    return PlayerStats(
        gamerTag = this.gamerTag ?: "Unknown",
        rank = "Level ${this.statsDetail?.rank ?: 0}",
        kills = this.statsDetail?.kills ?: 0,
        deaths = this.statsDetail?.deaths ?: 0,
        wins = this.statsDetail?.wins ?: 0,    // New
        losses = this.statsDetail?.losses ?: 0 // New
    )
}