package com.fernandoherrera.hackernewstestapp.domain

import com.fernandoherrera.hackernewstestapp.domain.model.Hit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchArticlesUseCase @Inject constructor(
    private val repository: HackerNewsRepository
) {
    suspend operator fun invoke(): Flow<Hit> {
        return repository.allHits()
    }
}