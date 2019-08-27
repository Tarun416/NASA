package com.example.core.data

import com.example.core.domain.PlanetaryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetaryRemoteSource {

    @GET("/apod")
    fun getPictures(@Query("api_key") apiKey : String) : Observable<PlanetaryResponse>

}