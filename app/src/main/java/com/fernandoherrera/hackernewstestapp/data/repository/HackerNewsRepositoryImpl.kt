package com.fernandoherrera.hackernewstestapp.data.repository

import com.fernandoherrera.hackernewstestapp.data.api.HackerNewsResponse
import com.fernandoherrera.hackernewstestapp.data.api.HackerNewsService
import com.fernandoherrera.hackernewstestapp.data.mapper.MapHitResponseToDomain
import com.fernandoherrera.hackernewstestapp.domain.HackerNewsRepository
import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewstestapp.domain.model.Hit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HackerNewsRepositoryImpl @Inject constructor(
    private val hackerNewsService: HackerNewsService
) : HackerNewsRepository {
    private val mapHitResponseToDomain = MapHitResponseToDomain()
    override suspend fun allHits(): Flow<Hit> {
        val remoteHits = hackerNewsService.fetchArticles().articles
        return flow {
            remoteHits.forEach { hitResponse ->
                val domainHit = mapHitResponseToDomain.map(hitResponse) // Uso del mapper
                emit(domainHit)
                //emit(hitResponse) // Convierte a modelo de dominio si es necesario
            }
        }
    }

    override suspend fun removeItemById(hitObjectId: String): Boolean {
        TODO("Not yet implemented")
    }
}