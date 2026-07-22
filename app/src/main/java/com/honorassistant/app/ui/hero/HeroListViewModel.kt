package com.honorassistant.app.ui.hero

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.honorassistant.app.data.repository.DefaultHeroRepository
import kotlinx.coroutines.launch

class HeroListViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = DefaultHeroRepository(application.applicationContext)
    private val _heroes = MutableLiveData<List<com.honorassistant.app.data.models.Hero>>(emptyList())
    val heroes: LiveData<List<com.honorassistant.app.data.models.Hero>> = _heroes
    fun loadHeroes() { viewModelScope.launch { _heroes.postValue(repo.getHeroes()) } }
    fun search(query: String) { viewModelScope.launch { _heroes.postValue(repo.searchHeroes(query)) } }
}
