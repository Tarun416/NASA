package com.example.nasa.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nasa.framework.db.dao.PlanetaryResponseDao
import com.example.nasa.framework.db.entity.PlanetaryResponseEntity

@Database(
    entities = [PlanetaryResponseEntity::class],
    version = 4,
    exportSchema = false
)
abstract class PlanetaryDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "planetary.db"

        private var instance: PlanetaryDatabase? = null

        private fun create(context: Context): PlanetaryDatabase =
            Room.databaseBuilder(context, PlanetaryDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): PlanetaryDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun planetaryDao(): PlanetaryResponseDao


}