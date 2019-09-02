package com.example.nasa.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.PlanetaryResponse

@Dao
interface PlanetaryResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlanetaryResponse(planetaryResponse: PlanetaryResponseEntity)

    @Query("SELECT * FROM PlanetaryResponseEntity")
    fun getList(): List<PlanetaryResponse>


}