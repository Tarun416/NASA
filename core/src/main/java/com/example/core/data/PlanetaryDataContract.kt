package com.example.core.data

import com.example.core.domain.PlanetaryResponse
import com.example.core.networking.Outcome
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface PlanetaryDataContract {

    interface Repository {
        val postFetchOutcome: PublishSubject<Outcome<List<PlanetaryResponse>>>
        fun fetchPictureFromDb()
        fun saveResp(resp : PlanetaryResponse)
        fun getPictures()
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun getPictures(): Single<PlanetaryResponse>
    }


    interface Local {
        fun getPlanetaryList(): Flowable<List<PlanetaryResponse>>
        fun savePlanetaryResponse(resp : PlanetaryResponse)
    }
}