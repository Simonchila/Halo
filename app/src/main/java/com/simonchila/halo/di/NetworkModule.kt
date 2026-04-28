package com.simonchila.halo.di

import android.content.Context
import coil.ImageLoader
import com.simonchila.halo.BuildConfig
import com.simonchila.halo.data.remote.AuthInterceptor
import com.simonchila.halo.data.remote.api.HaloApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.haloapi.com/"

    // 1. Create a shared client so both API and Images use the AuthInterceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
        .build()

    // 2. Existing service creation logic
    fun createService(): HaloApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(HaloApiService::class.java)
    }

    // 3. New function to provide the Coil ImageLoader
    fun createImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(okHttpClient) // Uses the same client with the API Key
            .crossfade(true) // Makes image appearance smoother
            .build()
    }
}