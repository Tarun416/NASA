package com.example.nasa.framework.di

import com.example.core.data.PlanetaryRemoteSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[ NetworkModule::class])
interface CoreComponent {

    fun planetartService() : PlanetaryRemoteSource

}