package com.example.nasa.framework.cache

import com.example.core.data.local.PlanetaryLocal
import com.example.core.domain.PlanetaryResponse
import com.example.nasa.framework.db.PlanetaryDatabase
import com.example.nasa.framework.db.entity.PlanetaryResponseEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class PlanetaryLocalImpl(private val planetaryDb: PlanetaryDatabase) : PlanetaryLocal {
    override fun savePictureIntoDb(resp: PlanetaryResponse) {
        Completable.fromAction {
            planetaryDb.planetaryDao().addPlanetaryResponse(
                PlanetaryResponseEntity(
                    resp.date!!,
                    resp.copyright,
                    resp.mediaType,
                    resp.hdurl,
                    resp.serviceVersion,
                    resp.explanation,
                    resp.title,
                    resp.url
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getPicturesFromDB(): Flowable<List<PlanetaryResponse>> {
        return planetaryDb.planetaryDao().getList()
    }


}