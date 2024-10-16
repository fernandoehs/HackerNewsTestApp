package com.fernandoherrera.hackernewstestapp.data.mapper

//import com.fernandoherrera.data.model.local.HitEntity
//import com.fernandoherrera.data.model.remote.HitResponse
//import com.fernandoherrera.domain.model.Hit
//import com.fernandoherrera.hackernewstestapp.data.mapper.Mapper
import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewstestapp.domain.model.Hit

//class MapHitDtoToEntity : Mapper<HitResponse, HitEntity> {
//    override fun map(from: HitResponse): HitEntity {
//        return HitEntity(
//            from.objectID,
//            from.storyTitle ?: from.title,
//            from.author,
//            from.createdAt,
//            from.url
//        )
//    }
//}

class MapHitResponseToDomain : Mapper<HitResponse, Hit> {
    override fun map(from: HitResponse): Hit {
        return Hit(
            from.objectID,
            from.storyTitle ?: from.title,
            from.author,
            from.createdAt,
            from.url
        )
    }
}
