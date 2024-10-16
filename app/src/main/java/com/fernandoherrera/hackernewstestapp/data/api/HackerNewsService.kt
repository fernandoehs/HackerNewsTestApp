package com.fernandoherrera.hackernewstestapp.data.api

import retrofit2.http.GET

interface HackerNewsService {
    @GET("search_by_date?query=mobile")
    suspend fun fetchArticles(): HackerNewsResponse
}