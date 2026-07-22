package com.honorassistant.app.data.models

data class LoreLocation(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val associatedHeroes: List<String>,
    val loreChapters: List<String>
)
