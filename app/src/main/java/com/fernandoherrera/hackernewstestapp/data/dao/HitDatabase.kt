package com.fernandoherrera.hackernewstestapp.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity

const val DATABASE_NAME = "HackerNewsDb.db"

@Database(
    entities = [HitEntity::class],
    version = 1
)
abstract class HitDatabase : RoomDatabase() {
    abstract fun getHitDao(): HitDao

    companion object {
        @Volatile
        private var instance: HitDatabase? = null

        fun getInstance(context: Context): HitDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): HitDatabase {
            return Room.databaseBuilder(context, HitDatabase::class.java, DATABASE_NAME).build()
        }
    }
}