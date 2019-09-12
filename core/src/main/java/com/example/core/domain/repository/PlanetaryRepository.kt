package com.example.core.domain.repository

import com.example.core.domain.PlanetaryResponse
import com.example.core.networking.Outcome
import io.reactivex.subjects.PublishSubject

interface PlanetaryRepository
{
    fun getPictureFromDb()
    val postFetchOutcome: PublishSubject<Outcome<List<PlanetaryResponse>>>
}