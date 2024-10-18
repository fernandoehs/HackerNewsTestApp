package com.fernandoherrera.hackernewstestapp.data.repository

import android.annotation.SuppressLint
import com.fernandoherrera.hackernewstestapp.data.api.HackerNewsService
import com.fernandoherrera.hackernewstestapp.data.datasource.HackerNewsLocalDataSource
import com.fernandoherrera.hackernewstestapp.data.mapper.MapHitEntityToHit
import com.fernandoherrera.hackernewstestapp.data.mapper.MapHitResponseToDomain
import com.fernandoherrera.hackernewstestapp.data.mapper.MapHitResponseToEntity
import com.fernandoherrera.hackernewstestapp.data.mapper.MapHitToEntity
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity
import com.fernandoherrera.hackernewstestapp.domain.HackerNewsRepository
import com.fernandoherrera.hackernewstestapp.domain.model.Hit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HackerNewsRepositoryImpl @Inject constructor(
    private val hackerNewsService: HackerNewsService,
    private val localDataSource: HackerNewsLocalDataSource
) : HackerNewsRepository {
    private val mapHitResponseToDomain = MapHitResponseToDomain()
    private val mapHitResponseToEntity = MapHitResponseToEntity()
    private val mapHitEntityToHit = MapHitEntityToHit()
    private val mapHitToEntity = MapHitToEntity()
    private val deletedItems = mutableSetOf<String>()
    @SuppressLint("SuspiciousIndentation")
    override suspend fun getAllHits(): Flow<Hit> = flow {
        try {
            val hitEntities = mutableListOf<HitEntity>()
            val remoteHits = hackerNewsService.fetchArticles().articles

            remoteHits.forEach { hitResponse ->
                val domainHit = mapHitResponseToDomain.map(hitResponse)
                val hitEntity = mapHitResponseToEntity.map(hitResponse)

                if (!hitEntities.contains(hitEntity) ){
                    if (!deletedItems.contains(hitEntity.objectId)){
                        emit(domainHit)
                        hitEntities.add(hitEntity)
                    }
                }
            }
            localDataSource.insertHits(hitEntities)

        } catch (e: Exception) {
            val localHits = localDataSource.getLocalHits(deletedItems)
            if (localHits.isNotEmpty()) {
                localHits.forEach { hitEntity ->

                    emit(mapHitEntityToHit.map(hitEntity))
                }
            } else {
                emitAll(emptyFlow())
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun removeItemById(hit: Hit) {
        deletedItems.add(mapHitToEntity.map(hit).objectId)
        localDataSource.removeItemById(mapHitToEntity.map(hit))
    }

}