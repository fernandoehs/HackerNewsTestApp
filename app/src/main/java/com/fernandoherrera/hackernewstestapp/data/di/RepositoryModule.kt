package com.fernandoherrera.hackernewstestapp.data.di

import com.fernandoherrera.hackernewstestapp.data.api.HackerNewsService
import com.fernandoherrera.hackernewstestapp.data.mapper.MapHitResponseToDomain
import com.fernandoherrera.hackernewstestapp.domain.HackerNewsRepository
import com.fernandoherrera.hackernewstestapp.data.repository.HackerNewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private const val BASE_URL = "https://hn.algolia.com/api/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHackerNewsService(retrofit: Retrofit): HackerNewsService {
        return retrofit.create(HackerNewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideHackerNewsRepository(
        hackerNewsService: HackerNewsService
    ): HackerNewsRepository {
        return HackerNewsRepositoryImpl(hackerNewsService)
    }
}
