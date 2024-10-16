package com.fernandoherrera.hackernewstestapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity

@Dao
interface HitDao {

    @Query("SELECT * FROM hitEntity")
    fun getHits(): List<HitEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(articles: List<HitEntity>)

    @Query("DELETE FROM hitEntity WHERE objectId = :articleId")
    suspend fun deleteArticle(articleId: Long)

    @Query("DELETE FROM hitEntity")
    suspend fun deleteAll()

}