package com.fernandoherrera.hackernewstestapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernandoherrera.hackernewstestapp.domain.FetchArticlesUseCase
//import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
//import com.fernandoherrera.hackernewstestapp.domain.model.Hit
//import com.fernandoherrera.domain.usecase.LocalListHitUseCase
import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewstestapp.data.repository.HackerNewsRepositoryImpl
import com.fernandoherrera.hackernewstestapp.domain.model.Hit
//import com.fernandoherrera.hackernewstestapp.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HackerNewsViewModel @Inject constructor(
   // private val repository: HackerNewsRepositoryImpl
    private val fetchArticlesUseCase: FetchArticlesUseCase
    // private val repository: LocalListHitUseCase

) : ViewModel() {

   //private val _news = MutableStateFlow<List<HitResponse>>(emptyList())
   // val news: StateFlow<List<HitResponse>> = _news
    private val _articles = MutableStateFlow<List<Hit>>(emptyList())
    val articles: StateFlow<List<Hit>> = _articles.asStateFlow()

    init {
        fetchArticles()
    }
    private fun fetchArticles() {
        viewModelScope.launch {
            fetchArticlesUseCase().collect { hit ->
                _articles.value = _articles.value + hit
            }
        }
    }

    fun deleteArticle(newItem: Hit) {
        _articles.value = _articles.value.filter { it != newItem }
    }
}
