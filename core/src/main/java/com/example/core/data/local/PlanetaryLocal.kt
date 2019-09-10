package com.example.core.data.local

import com.example.core.domain.PlanetaryResponse
import io.reactivex.Flowable

interface PlanetaryLocal
{
    fun getPicturesFromDB() : Flowable<List<PlanetaryResponse>>

    fun savePictureIntoDb(resp : PlanetaryResponse)
}