package com.example.nasa.framework

import com.example.core.data.local.PlanetaryLocal
import com.example.core.domain.PlanetaryResponse
import com.example.nasa.framework.db.NasaDatabase
import com.example.nasa.framework.db.PlanetaryResponseDao
import com.example.nasa.framework.db.PlanetaryResponseEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class PlanetaryLocalData(private val planetaryDb : NasaDatabase) : PlanetaryLocal {
    override fun savePictureIntoDb(resp: PlanetaryResponse) {
        Completable.fromAction {
            planetaryDb.planetaryDao().addPlanetaryResponse(PlanetaryResponseEntity(resp.date!!,resp.copyright,resp.mediaType,resp.hdurl,resp.serviceVersion,resp.explanation,resp.title,resp.url))
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getPicturesFromDB(): Flowable<List<PlanetaryResponse>> {
        return planetaryDb.planetaryDao().getList()
    }


}