package com.fernandoherrera.hackernewstestapp.domain

import com.fernandoherrera.hackernewstestapp.domain.model.Hit
import kotlinx.coroutines.flow.Flow

interface HackerNewsRepository {
    suspend fun getAllHits(): Flow<Hit>
    suspend fun removeItemById(hit: Hit)
}
