package com.example.nasa.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PlanetaryResponseEntity::class],
    version = 4,
    exportSchema = false
)
abstract class NasaDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "planetary.db"

        private var instance: NasaDatabase? = null

        private fun create(context: Context): NasaDatabase =
            Room.databaseBuilder(context, NasaDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): NasaDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun planetaryDao(): PlanetaryResponseDao


}