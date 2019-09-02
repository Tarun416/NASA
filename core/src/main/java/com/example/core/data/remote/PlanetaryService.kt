package com.example.core.data.remote

import com.example.core.domain.PlanetaryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetaryService {

    @GET("/planetary/apod")
    fun getPictures(@Query("api_key") apiKey : String) : Single<PlanetaryResponse>

}