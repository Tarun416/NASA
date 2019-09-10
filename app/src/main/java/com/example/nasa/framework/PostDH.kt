package com.example.nasa.framework

import com.example.nasa.presentation.list.di.DaggerListComponent
import com.example.nasa.presentation.list.di.ListComponent
import javax.inject.Singleton

@Singleton
object PostDH {
    private var listComponent: ListComponent? = null


    fun listComponent(): ListComponent {
        if (listComponent == null)
            listComponent = DaggerListComponent.builder().coreComponent(NasaApplication.coreComponent).build()
        return listComponent as ListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }


}