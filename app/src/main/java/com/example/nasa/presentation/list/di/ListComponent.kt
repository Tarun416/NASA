package com.example.nasa.presentation.list.di

import android.content.Context
import com.example.core.data.remote.PlanetaryService
import com.example.core.interactors.GetPictures
import com.example.nasa.framework.cache.PlanetaryLocalImpl
import com.example.core.data.PlanetaryRemoteImpl
import com.example.nasa.framework.db.PlanetaryDatabase
import com.example.core.di.CoreComponent
import com.example.core.domain.repository.PlanetaryRepository
import com.example.nasa.framework.PlanetaryDataRepository
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
        compositeDisposable: CompositeDisposable,
        repository : PlanetaryDataRepository
    ) = ListViewModelFactory(interactors, compositeDisposable,repository)

    @Provides
    @ListScope
    fun interactors(planetaryRepo: PlanetaryDataRepository) = GetPictures(planetaryRepo)


    @Provides
    @ListScope
    fun listrepo(
        local: PlanetaryLocalImpl,
        remote: PlanetaryRemoteImpl
    ): PlanetaryDataRepository = PlanetaryDataRepository(remote,local)


    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @ListScope
    fun remoteData(planetaryService: PlanetaryService): PlanetaryRemoteImpl =
        PlanetaryRemoteImpl(planetaryService)

    @Provides
    @ListScope
    fun localData(planetaryDb: PlanetaryDatabase): PlanetaryLocalImpl =
        PlanetaryLocalImpl(planetaryDb)

    @Provides
    @ListScope
    fun planetaryDb(context: Context): PlanetaryDatabase = PlanetaryDatabase.getInstance(context)

    @Provides
    @ListScope
    fun postService(retrofit: Retrofit): PlanetaryService =
        retrofit.create(PlanetaryService::class.java)
}