package com.fernandoherrera.hackernewstestapp.data.mapper

import com.fernandoherrera.hackernewstestapp.data.model.local.HitEntity
import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewstestapp.domain.model.Hit

class MapHitResponseToEntity : Mapper<HitResponse, HitEntity> {
    override fun map(from: HitResponse): HitEntity {
        return HitEntity(
            from.objectID,
            from.storyTitle ?: from.title,
            from.author,
            from.createdAt,
            from.url
        )
    }
}

class MapHitEntityToHit : Mapper<HitEntity,Hit> {
    override fun map(from: HitEntity): Hit{
        return Hit(
            from.objectId,
            from.storyTitle,
            from.author,
            from.createdAt,
            from.storyUrl
        )
    }
}

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

class MapHitToEntity : Mapper<Hit, HitEntity> {
    override fun map(from: Hit): HitEntity {
        return HitEntity(
            objectId = from.objectId,
            storyTitle = from.storyTitle,
            author = from.author,
            createdAt = from.createdAt,
            storyUrl = from.storyUrl
        )
    }
}
