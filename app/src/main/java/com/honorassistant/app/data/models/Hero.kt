package com.honorassistant.app.data.models

data class Skill(
    val name: String,
    val description: String,
    val iconUrl: String,
    val cooldown: String,
    val cost: String
)

data class Skin(
    val name: String,
    val tier: String,
    val imageUrl: String
)

data class Hero(
    val id: String,
    val name: String,
    val title: String,
    val lore: String,
    val role: String,
    val difficulty: Int,
    val splashArtUrl: String,
    val skills: List<Skill>,
    val skins: List<Skin>
)
