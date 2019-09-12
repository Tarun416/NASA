package com.example.nasa.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.PlanetaryResponse
import com.example.nasa.framework.db.entity.PlanetaryResponseEntity
import io.reactivex.Flowable

@Dao
interface PlanetaryResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlanetaryResponse(planetaryResponse: PlanetaryResponseEntity)

    @Query("SELECT * FROM PlanetaryResponseEntity")
    fun getList(): Flowable<List<PlanetaryResponse>>

}