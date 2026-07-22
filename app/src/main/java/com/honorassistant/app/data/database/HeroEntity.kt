package com.honorassistant.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.honorassistant.app.data.models.Skill
import com.honorassistant.app.data.models.Skin

@Entity(tableName = "heroes")
data class HeroEntity(
    @PrimaryKey val id: String,
    val name: String,
    val title: String,
    val lore: String,
    val role: String,
    val difficulty: Int,
    val splashArtUrl: String,
    val skillsJson: String,
    val skinsJson: String
)

class Converters {
    @TypeConverter
    fun fromSkillList(value: List<Skill>): String = Gson().toJson(value)
    @TypeConverter
    fun toSkillList(value: String): List<Skill> = Gson().fromJson(value, Array<Skill>::class.java).toList()
    @TypeConverter
    fun fromSkinList(value: List<Skin>): String = Gson().toJson(value)
    @TypeConverter
    fun toSkinList(value: String): List<Skin> = Gson().fromJson(value, Array<Skin>::class.java).toList()
}
