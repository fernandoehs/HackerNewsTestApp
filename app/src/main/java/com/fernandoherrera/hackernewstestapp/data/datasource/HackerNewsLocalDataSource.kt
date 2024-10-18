package com.fernandoherrera.hackernewstestapp.data.datasource

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity

interface HackerNewsLocalDataSource {
    suspend fun getLocalHits(deletedIds: MutableSet<String>): List<HitEntity>
    suspend fun getRemotedHits(deletedIds: MutableSet<String>): List<HitEntity>
    suspend fun clearLocalHits()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHits(hits: List<HitEntity>)
    suspend fun removeItemById(hit: HitEntity)
}
