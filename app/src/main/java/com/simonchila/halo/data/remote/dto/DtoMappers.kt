package com.simonchila.halo.data.remote.dto

import com.simonchila.halo.data.local.entities.PlayerStats

fun PlayerResultDto.toEntity(): PlayerStats {
    return PlayerStats(
        gamerTag = this.gamerTag,
        rank = "Level ${this.statsDetail.rank}",
        kills = this.statsDetail.kills,
        deaths = this.statsDetail.deaths
    )
}