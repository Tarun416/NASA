package com.example.nasa.framework

import com.example.core.data.remote.PlanetaryService
import com.example.core.domain.PlanetaryResponse
import io.reactivex.Single

class PlanetaryRemoteData(private val plantaryService : PlanetaryService) : PlanetaryService {
    override fun getPictures(apiKey: String): Single<PlanetaryResponse> {
        return plantaryService.getPictures(apiKey)
    }


    }

