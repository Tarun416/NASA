package com.example.core.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules =[ NetworkModule::class])
interface CoreComponent {

    fun retrofit() : Retrofit

}