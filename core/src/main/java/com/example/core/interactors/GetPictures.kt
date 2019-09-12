package com.example.core.interactors

import com.example.core.domain.repository.PlanetaryRepository
import com.example.core.domain.PlanetaryResponse
import com.example.core.networking.Outcome
import io.reactivex.subjects.PublishSubject

class GetPictures(private val planetaryRepository: PlanetaryRepository)
{
    operator fun invoke() = planetaryRepository.getPictureFromDb()



}