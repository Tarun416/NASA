package com.example.core.data

import com.example.core.data.remote.PlanetaryRemote
import com.example.core.data.remote.PlanetaryService
import com.example.core.domain.PlanetaryResponse
import io.reactivex.Single

class PlanetaryRemoteImpl(private val plantaryService : PlanetaryService) : PlanetaryRemote {
    override fun getPictures(apiKey: String): Single<PlanetaryResponse> {
        return plantaryService.getPictures(apiKey)
    }


    }

