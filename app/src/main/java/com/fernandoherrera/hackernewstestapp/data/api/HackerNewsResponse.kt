package com.fernandoherrera.hackernewstestapp.data.api

import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
import com.google.gson.annotations.SerializedName

data class HackerNewsResponse(
    @SerializedName("hits") val articles: List<HitResponse>,
)