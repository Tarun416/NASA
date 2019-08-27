package com.example.nasa.framework

import android.app.Application
import com.example.core.di.CoreComponent

class NasaApplication : Application()
{

    companion object {
        lateinit var coreComponent: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        // coreComponent = DaggerCoreComponent.builder().build()
    }

}