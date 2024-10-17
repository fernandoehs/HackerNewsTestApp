package com.fernandoherrera.hackernewstestapp.data.datasource

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity

interface HackerNewsLocalDataSource {
    suspend fun getLocalHits(): List<HitEntity>
    suspend fun getRemotedHits(): List<HitEntity>
    suspend fun clearLocalHits()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHits(hits: List<HitEntity>)
    suspend fun removeItemById(hitObjectId: String): Boolean
}
