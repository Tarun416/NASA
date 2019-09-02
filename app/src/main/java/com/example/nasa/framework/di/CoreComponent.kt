package com.example.nasa.framework.di

import android.content.Context
import com.example.core.data.remote.PlanetaryService
import com.example.nasa.framework.PlanetaryRemoteData
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules =[ NetworkModule::class, AppModule::class])
interface CoreComponent {
    fun retorfit() : Retrofit
    fun context() : Context
}