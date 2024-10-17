package com.fernandoherrera.hackernewstestapp.domain.model

data class Hit(
    val objectId: String,
    val storyTitle: String?,
    val author: String,
    val createdAt: String,
    val storyUrl: String?
)

