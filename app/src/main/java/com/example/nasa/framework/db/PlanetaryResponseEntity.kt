package com.example.nasa.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
@Entity
data class PlanetaryResponseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("media_type")
    val mediaType: String? = null,

    @field:SerializedName("hdurl")
    val hdurl: String? = null,

    @field:SerializedName("service_version")
    val serviceVersion: String? = null,

    @field:SerializedName("explanation")
    val explanation: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)