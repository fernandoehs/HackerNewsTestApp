package com.fernandoherrera.hackernewstestapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernandoherrera.hackernewstestapp.domain.HackerNewsRepository
import com.fernandoherrera.hackernewstestapp.domain.model.Hit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HackerNewsViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {
    private val _articles = MutableStateFlow<List<Hit>>(emptyList())
    val articles: StateFlow<List<Hit>> = _articles.asStateFlow()

    init {
        fetchArticles()
    }
    private fun fetchArticles() {
        viewModelScope.launch {
            repository.allHits().collect{ hit ->
                _articles.value = _articles.value + hit
            }
        }
    }

    fun deleteArticle(newItem: Hit) {
        _articles.value = _articles.value.filter { it != newItem }
    }
}
