package com.honorassistant.app.data.network

import com.honorassistant.app.data.database.HeroEntity
import retrofit2.http.GET

interface HeroApiService {
    @GET("heroes")
    suspend fun getHeroes(): List<HeroEntity>
}
