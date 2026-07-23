package com.honorassistant.app.data.network

import com.honorassistant.app.data.models.Hero
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApiService {
    @GET("heroes")
    suspend fun getHeroes(): List<Hero>

    @GET("heroes/{id}")
    suspend fun getHero(@Path("id") id: String): Hero
}
