package com.fernandoherrera.hackernewstestapp.data.datasource


import com.fernandoherrera.hackernewstestapp.data.dao.HitDao
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HackerNewsLocalDataSourceImpl @Inject constructor(
    private val hitDao: HitDao
) : HackerNewsLocalDataSource {

    override suspend fun getLocalHits(): List<HitEntity> {
        return withContext(Dispatchers.IO) {
            hitDao.getHits()
        }
    }

    override suspend fun getRemotedHits(): List<HitEntity> {
        return hitDao.getHits()
    }

    override suspend fun clearLocalHits() {
        hitDao.deleteAll()
    }

    override suspend fun insertHits(hits: List<HitEntity>) {
        //val removedHits = database.getHitDao().deleteAll()
//        if (removedHits.isNotEmpty()) {
//            hits.filter {
//                removedHits.contains(RemovedHitEntity(it.objectId)).not()
//            }
//        }
        hitDao.insertAll(hits)
    }

    override suspend fun removeItemById(hitObjectId: String): Boolean {
        TODO("Not yet implemented")
    }

}
