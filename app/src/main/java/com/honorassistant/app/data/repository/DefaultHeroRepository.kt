package com.honorassistant.app.data.repository

import android.content.Context
import com.honorassistant.app.data.database.AppDatabase
import com.honorassistant.app.data.database.Converters
import com.honorassistant.app.data.database.HeroEntity
import com.honorassistant.app.data.models.Hero
import com.honorassistant.app.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultHeroRepository(private val context: Context) : HeroRepository {
    private val dao = AppDatabase.getInstance(context).heroDao()
    private val api = RetrofitClient.api

    override suspend fun getHeroes(): List<Hero> = withContext(Dispatchers.IO) {
        try {
            val network = api.getHeroes()
            dao.clearAll()
            dao.insertAll(network)
            network.map { it.toDomain() }
        } catch (e: Exception) {
            dao.getAll().map { it.toDomain() }
        }
    }

    override suspend fun searchHeroes(query: String): List<Hero> = withContext(Dispatchers.IO) {
        if (query.isBlank()) getHeroes() else dao.search("%$query%").map { it.toDomain() }
    }

    override suspend fun getHeroById(id: String): Hero? = withContext(Dispatchers.IO) {
        dao.getAll().firstOrNull { it.id == id }?.toDomain()
    }

    private fun HeroEntity.toDomain(): Hero {
        val converter = Converters()
        return Hero(id, name, title, lore, role, difficulty, splashArtUrl,
            converter.toSkillList(skillsJson), converter.toSkinList(skinsJson))
    }
}

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
    suspend fun searchHeroes(query: String): List<Hero>
    suspend fun getHeroById(id: String): Hero?
}
