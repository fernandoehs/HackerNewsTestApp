package com.fernandoherrera.hackernewstestapp.presentation.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var state by mutableStateOf(HomeState())

    init {
        fetchArticles()
    }
    fun fetchArticles() {
        state = state.copy(isLoading = true)

        viewModelScope.launch {
            try {
                repository.allHits().collect { hit ->
                    _articles.value = _articles.value + hit
                }
            } catch (e: Exception) {
                Log.e("HackerNewsRepository", "Error al obtener hits", e)
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }
//    private fun fetchArticles() {
//        state = state.copy(
//            isLoading = true
//        )
//        viewModelScope.launch {
//            repository.allHits().collect{ hit ->
//                _articles.value = _articles.value + hit
//            }
//            state = state.copy(
//                isLoading = false
//            )
//        }
//    }

    fun deleteArticle(newItem: Hit) {
        _articles.value = _articles.value.filter { it != newItem }
    }
}
