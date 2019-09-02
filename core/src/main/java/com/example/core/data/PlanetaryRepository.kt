package com.example.core.data

import com.example.core.domain.PlanetaryResponse

class PlanetaryRepository(val local: PlanetaryDataContract.Repository)  {

    fun getPictureFromDb() = local.fetchPictureFromDb()

  /*  fun getPicturesFromDb() = local.getPlanetaryList()

    fun savePlanetaryResponseIntoDb(resp : PlanetaryResponse) = local.savePlanetaryResponse(resp)*/
}