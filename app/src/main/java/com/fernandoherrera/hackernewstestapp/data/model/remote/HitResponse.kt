package com.fernandoherrera.hackernewstestapp.data.model.remote

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class HitResponse(
    @PrimaryKey val objectID: String,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("author") val author: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("story_url") val url: String?,
)

