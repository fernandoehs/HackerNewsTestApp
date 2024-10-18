package com.fernandoherrera.hackernewstestapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity

@Dao
interface HitDao {
    @Query("SELECT * FROM hitEntity WHERE objectId NOT IN (:deletedIds)")
    fun getAllHits(deletedIds: MutableSet<String>): List<HitEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(articles: List<HitEntity>)

    @Delete
    //suspend fun deleteArticle(articleId: Long)
    suspend fun deleteArticle(hit: HitEntity)

    @Query("DELETE FROM hitEntity")
    suspend fun deleteAll()

}