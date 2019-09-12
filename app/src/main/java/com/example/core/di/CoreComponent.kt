package com.example.core.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules =[ NetworkModule::class, AppModule::class])
interface CoreComponent {
    fun retorfit() : Retrofit
    fun context() : Context
}