package com.fernandoherrera.hackernewstestapp.domain

import android.util.Log
import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewstestapp.domain.model.Hit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchArticlesUseCase @Inject constructor(
    private val repository: HackerNewsRepository
) {
    suspend operator fun invoke(): Flow<Hit> {
        Log.d("FetchArticlesUseCase", "Fetching articles from repository")
        return repository.allHits()
    }
}