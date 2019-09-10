package com.example.nasa.presentation.list.di

import android.content.Context
import com.example.core.data.PlanetaryRepository
import com.example.core.data.remote.PlanetaryService
import com.example.core.interactors.GetPictures
import com.example.nasa.framework.PlanetaryLocalData
import com.example.nasa.framework.PlanetaryRemoteData
import com.example.nasa.framework.db.NasaDatabase
import com.example.nasa.framework.di.CoreComponent
import com.example.nasa.presentation.detail.DetailsFragment
import com.example.nasa.presentation.list.ListViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@ListScope
@Component(dependencies = [CoreComponent::class], modules = [ListModule::class])
interface ListComponent {
    fun planetaryService(): PlanetaryService
    fun inject(fragment: com.example.nasa.presentation.list.ListFragment)
    fun inject(fragment: DetailsFragment)
}


@Module
@ListScope
class ListModule {
    /*ViewModel*/
    @Provides
    @ListScope
    fun listViewModelFactory(
        interactors: GetPictures,
        compositeDisposable: CompositeDisposable
    ) = ListViewModelFactory(interactors, compositeDisposable)

    @Provides
    @ListScope
    fun interactors(planetaryRepo: PlanetaryRepository) = GetPictures(planetaryRepo)


    @Provides
    @ListScope
    fun listrepo(
        local: PlanetaryLocalData,
        remote: PlanetaryRemoteData
    ): PlanetaryRepository = PlanetaryRepository(remote,local)


    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @ListScope
    fun remoteData(planetaryService: PlanetaryService): PlanetaryRemoteData =
        PlanetaryRemoteData(planetaryService)

    @Provides
    @ListScope
    fun localData(planetaryDb: NasaDatabase): PlanetaryLocalData =
        PlanetaryLocalData(planetaryDb)

    @Provides
    @ListScope
    fun planetaryDb(context: Context): NasaDatabase = NasaDatabase.getInstance(context)

    @Provides
    @ListScope
    fun postService(retrofit: Retrofit): PlanetaryService =
        retrofit.create(PlanetaryService::class.java)
}