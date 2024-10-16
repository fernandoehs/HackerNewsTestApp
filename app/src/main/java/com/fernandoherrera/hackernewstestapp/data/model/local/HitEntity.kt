package com.fernandoherrera.hackernewstestapp.data.model.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "hitEntity", indices = [Index(value = ["storyTitle"], unique = true)])
data class HitEntity(
    @PrimaryKey
    val objectId: String,
    val storyTitle: String?,
    val author: String,
    val createdAt: String,
    val storyUrl: String
)