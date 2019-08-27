package com.example.core.data

import com.example.core.domain.PlanetaryResponse
import io.reactivex.Observable

class PlanetaryRepository(val planetaryRemoteSource: PlanetaryRemoteSource) {

    fun getPictures(apiKey: String) = planetaryRemoteSource.getPictures(apiKey)
}