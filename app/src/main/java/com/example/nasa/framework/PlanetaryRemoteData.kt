package com.example.nasa.framework

import com.example.core.data.PlanetaryDataContract
import com.example.core.data.remote.PlanetaryService
import com.example.core.domain.PlanetaryResponse
import io.reactivex.Single

class PlanetaryRemoteData(private val plantaryService : PlanetaryService) : PlanetaryDataContract.Remote {

    override fun getPictures(): Single<PlanetaryResponse>  = plantaryService.getPictures("JfQ2g8NWy92kBwRU34FmvkHKK6GFmUHEyQUqBue4")

    }

