package com.fernandoherrera.hackernewstestapp.data.datasource


import com.fernandoherrera.hackernewstestapp.data.dao.HitDatabase
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity

class HackerNewsLocalDataSourceImpl(
    private val database: HitDatabase,
) : HackerNewsLocalDataSource {

    override fun getLocalHits(query: String?): List<HitEntity> {
        return database.getHitDao().getHits()
    }

    override suspend fun getRemotedHits(): List<HitEntity> {
        return database.getHitDao().getHits()
    }

    override suspend fun clearLocalHits() {
        database.getHitDao().deleteAll()
    }

    override suspend fun insertHits(hits: MutableList<HitEntity>) {
        //val removedHits = database.getHitDao().deleteAll()
//        if (removedHits.isNotEmpty()) {
//            hits.filter {
//                removedHits.contains(RemovedHitEntity(it.objectId)).not()
//            }
//        }
        database.getHitDao().insertAll(hits)
    }

    override suspend fun removeItemById(hitObjectId: String): Boolean {
        TODO("Not yet implemented")
    }

}
