package com.fernandoherrera.hackernewstestapp.data.datasource

import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity

interface HackerNewsLocalDataSource {
    fun getLocalHits(query: String?): List<HitEntity>
    suspend fun getRemotedHits(): List<HitEntity>
    suspend fun clearLocalHits()
    suspend fun insertHits(hits: MutableList<HitEntity>)
    suspend fun removeItemById(hitObjectId: String): Boolean
}
