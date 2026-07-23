package com.honorassistant.app.data.repository

import android.content.Context
import com.honorassistant.app.data.database.AppDatabase
import com.honorassistant.app.data.database.HeroEntity
import com.honorassistant.app.data.datasource.LocalHeroDataSource
import com.honorassistant.app.data.models.Hero
import com.honorassistant.app.data.network.RetrofitClient
import com.google.gson.Gson
import com.honorassistant.app.data.models.Skill
import com.honorassistant.app.data.models.Skin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultHeroRepository(private val context: Context) {

    private val heroDao by lazy { AppDatabase.getInstance(context).heroDao() }
    private val gson = Gson()

    fun getHeroes(): Flow<List<Hero>> = flow {
        // 1. Emit from Room cache first (if any)
        val cached = heroDao.getAll()
        if (cached.isNotEmpty()) {
            emit(cached.map { it.toHero() })
        }

        // 2. Try network (MockInterceptor returns data always)
        try {
            val remote = RetrofitClient.heroApiService.getHeroes()
            // Cache in Room
            heroDao.clearAll()
            heroDao.insertAll(remote.map { it.toEntity() })
            emit(remote)
        } catch (e: Exception) {
            // 3. Fallback to static local data source
            if (cached.isEmpty()) {
                emit(LocalHeroDataSource.provideHeroes())
            }
        }
    }

    suspend fun getHeroById(id: String): Hero? {
        return try {
            RetrofitClient.heroApiService.getHero(id)
        } catch (e: Exception) {
            heroDao.getAll().find { it.id == id }?.toHero()
                ?: LocalHeroDataSource.provideHeroes().find { it.id == id }
        }
    }

    suspend fun searchHeroes(query: String): List<Hero> {
        val dbQuery = "%$query%"
        val dbResults = heroDao.search(dbQuery)
        return if (dbResults.isNotEmpty()) {
            dbResults.map { it.toHero() }
        } else {
            LocalHeroDataSource.provideHeroes().filter {
                it.name.contains(query) || it.title.contains(query)
            }
        }
    }

    private fun HeroEntity.toHero(): Hero = Hero(
        id = id,
        name = name,
        title = title,
        lore = lore,
        role = role,
        difficulty = difficulty,
        splashArtUrl = splashArtUrl,
        skills = gson.fromJson(skillsJson, Array<Skill>::class.java).toList(),
        skins = gson.fromJson(skinsJson, Array<Skin>::class.java).toList()
    )

    private fun Hero.toEntity(): HeroEntity = HeroEntity(
        id = id,
        name = name,
        title = title,
        lore = lore,
        role = role,
        difficulty = difficulty,
        splashArtUrl = splashArtUrl,
        skillsJson = gson.toJson(skills),
        skinsJson = gson.toJson(skins)
    )
}
