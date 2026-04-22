package com.simonchila.halo.di

import android.R.attr.apiKey
import com.simonchila.halo.BuildConfig
import com.simonchila.halo.data.remote.AuthInterceptor
import com.simonchila.halo.data.remote.api.HaloApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.haloapi.com/"
    fun createService(): HaloApiService {

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(HaloApiService::class.java)
    }
}