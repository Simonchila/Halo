package com.simonchila.halo.di

import com.simonchila.halo.data.remote.AuthInterceptor
import com.simonchila.halo.data.remote.api.HaloApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.haloapi.com/"

    fun createService(apiKey: String): HaloApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(apiKey))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(HaloApiService::class.java)
    }
}