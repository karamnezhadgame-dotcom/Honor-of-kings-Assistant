package com.honorassistant.app.ui.hero

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.honorassistant.app.data.models.Hero
import com.honorassistant.app.data.repository.DefaultHeroRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HeroListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DefaultHeroRepository(application)

    private val _allHeroes = MutableLiveData<List<Hero>>()
    private val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> = _heroes

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val searchQuery = MutableLiveData<String>("")

    init {
        loadHeroes()
    }

    private fun loadHeroes() {
        viewModelScope.launch {
            _loading.value = true
            repository.getHeroes().collectLatest { list ->
                _allHeroes.value = list
                applySearch(searchQuery.value ?: "")
                _loading.value = false
            }
        }
    }

    fun onSearchChanged(query: String) {
        searchQuery.value = query
        applySearch(query)
    }

    private fun applySearch(query: String) {
        val all = _allHeroes.value ?: return
        _heroes.value = if (query.isBlank()) all
        else all.filter { it.name.contains(query) || it.title.contains(query) }
    }
}
