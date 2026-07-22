package com.honorassistant.app.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.honorassistant.com/"
    private val client = OkHttpClient.Builder()
        .addInterceptor(MockInterceptor())
        .addInterceptor(okhttp3.logging.HttpLoggingInterceptor().apply {
            level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
        })
        .build()
    val api: HeroApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroApiService::class.java)
    }
}
