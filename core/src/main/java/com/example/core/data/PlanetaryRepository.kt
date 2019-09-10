package com.example.core.data

import com.example.core.data.local.PlanetaryLocal
import com.example.core.data.remote.PlanetaryService
import com.example.core.domain.PlanetaryResponse

class PlanetaryRepository(val remote: PlanetaryService , val local : PlanetaryLocal)  {

    fun getPictureFromDb() = local.getPicturesFromDB()
    fun getPictureFromServer(apikey : String) = remote.getPictures(apikey)
    fun savePictureIntoDb(response: PlanetaryResponse) = local.savePictureIntoDb(response)

}