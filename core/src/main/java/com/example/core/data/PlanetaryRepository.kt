package com.example.core.data

class PlanetaryRepository(val remote: PlanetaryDataContract.Repository) {

    fun getPictures() = remote.getPictures()
}