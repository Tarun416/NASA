package com.example.core.data.remote

import com.example.core.domain.PlanetaryResponse
import io.reactivex.Single
import retrofit2.http.Query

interface PlanetaryRemote
{
    fun getPictures(@Query("api_key") apiKey : String) : Single<PlanetaryResponse>
}