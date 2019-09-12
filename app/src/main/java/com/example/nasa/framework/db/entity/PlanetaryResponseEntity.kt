package com.example.nasa.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PlanetaryResponseEntity(

    @PrimaryKey
    val date: String,


    val copyright: String? = null,


    val mediaType: String? = null,


    val hdurl: String? = null,


    val serviceVersion: String? = null,


    val explanation: String? = null,


    val title: String? = null,


    val url: String? = null
)