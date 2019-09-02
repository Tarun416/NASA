package com.example.core.interactors

import com.example.core.data.PlanetaryRepository

class GetPictures(private val planetaryRepository: PlanetaryRepository)
{
    operator fun invoke() = planetaryRepository.getPictures()
}