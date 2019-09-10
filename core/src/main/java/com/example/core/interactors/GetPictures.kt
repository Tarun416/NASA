package com.example.core.interactors

import com.example.core.data.PlanetaryRepository
import com.example.core.domain.PlanetaryResponse

class GetPictures(private val planetaryRepository: PlanetaryRepository)
{
    operator fun invoke() = planetaryRepository.getPictureFromDb()

    operator fun invoke(apikey : String) = planetaryRepository.getPictureFromServer(apikey)

    operator fun invoke(response : PlanetaryResponse) = planetaryRepository.savePictureIntoDb(response)
}