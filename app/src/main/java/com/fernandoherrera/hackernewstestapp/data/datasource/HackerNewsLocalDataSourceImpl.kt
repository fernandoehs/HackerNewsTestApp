package com.fernandoherrera.hackernewstestapp.data.datasource

import com.fernandoherrera.hackernewstestapp.data.dao.HitDao
import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HackerNewsLocalDataSourceImpl @Inject constructor(
    private val hitDao: HitDao
) : HackerNewsLocalDataSource {

    override suspend fun getLocalHits(deletedIds: MutableSet<String>): List<HitEntity> {
        return withContext(Dispatchers.IO) {
            hitDao.getAllHits(deletedIds)
        }
    }

    override suspend fun getRemotedHits(deletedIds: MutableSet<String>): List<HitEntity> {
        return hitDao.getAllHits(deletedIds)
    }

    override suspend fun clearLocalHits() {
        hitDao.deleteAll()
    }

    override suspend fun insertHits(hits: List<HitEntity>) {
//        if (removedHits.isNotEmpty()) {
//            hits.filter {
//                removedHits.contains(RemovedHitEntity(it.objectId)).not()
//            }
//        }
        hitDao.insertAll(hits)
    }

    override suspend fun removeItemById(hit: HitEntity) {
       hitDao.deleteArticle(hit)
    }

//    override suspend fun removeItemById(hit: HitEntity) {
//        deletedItems.add(hit.objectId)
//        hitDao.deleteHit(hit)
//    }

}
