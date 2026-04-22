package com.simonchila.halo.data.remote.api

import com.simonchila.halo.data.remote.dto.HaloResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HaloApiService {
    @GET("stats/h5/servicerecords/arena")
    suspend fun getStats(@Query("players") players: String): Response<HaloResponseDto>
}