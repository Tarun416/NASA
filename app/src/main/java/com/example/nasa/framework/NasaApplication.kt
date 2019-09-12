package com.example.nasa.framework

import android.app.Application
import com.example.core.di.AppModule
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.facebook.stetho.Stetho

class NasaApplication : Application()
{

    companion object {
        lateinit var coreComponent: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        Stetho.initializeWithDefaults(this);
    }

    private fun initDI() {
         coreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()
    }

}