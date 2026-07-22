package com.honorassistant.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroes: List<HeroEntity>)
    @Query("SELECT * FROM heroes")
    suspend fun getAll(): List<HeroEntity>
    @Query("SELECT * FROM heroes WHERE name LIKE :query OR title LIKE :query")
    suspend fun search(query: String): List<HeroEntity>
    @Query("DELETE FROM heroes")
    suspend fun clearAll()
}
