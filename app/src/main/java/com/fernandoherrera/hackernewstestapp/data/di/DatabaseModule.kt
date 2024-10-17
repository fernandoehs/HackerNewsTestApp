package com.fernandoherrera.hackernewstestapp.data.di

import android.content.Context
import androidx.room.Room
import com.fernandoherrera.hackernewstestapp.data.dao.HitDao
import com.fernandoherrera.hackernewstestapp.data.dao.HitDatabase
import com.fernandoherrera.hackernewstestapp.data.datasource.HackerNewsLocalDataSource
import com.fernandoherrera.hackernewstestapp.data.datasource.HackerNewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HitDatabase {
        return Room.databaseBuilder(context, HitDatabase::class.java, "HackerNewsDb.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideHitDao(database: HitDatabase): HitDao {
        return database.getHitDao()
    }

    @Provides
    fun provideHackerNewsLocalDataSource(
        hitDao: HitDao
    ): HackerNewsLocalDataSource {
        return HackerNewsLocalDataSourceImpl(hitDao)
    }
}
