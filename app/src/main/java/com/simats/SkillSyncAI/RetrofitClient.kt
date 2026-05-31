package com.simats.SkillSyncAI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // For REAL DEVICE testing on the same WiFi: use your laptop's local IP
    private const val BASE_URL = "http://10.68.220.252:8000/"
    // For EMULATOR testing, use this instead:
    // private const val BASE_URL = "http://10.0.2.2:8000/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}