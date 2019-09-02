package com.example.nasa.framework.list.di

import android.content.Context
import com.example.core.data.PlanetaryDataContract
import com.example.core.data.PlanetaryRepository
import com.example.core.data.remote.PlanetaryService
import com.example.core.interactors.GetPictures
import com.example.nasa.framework.PlanetaryLocalData
import com.example.nasa.framework.PlanetaryRemoteData
import com.example.nasa.framework.PlanetaryRepoImpl
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
        compositeDisposable: CompositeDisposable,
        repo: PlanetaryDataContract.Repository
    ) = ListViewModelFactory(interactors, compositeDisposable, repo)

    @Provides
    @ListScope
    fun interactors(planetaryRepo: PlanetaryRepository) = GetPictures(planetaryRepo)

    @Provides
    @ListScope
    fun planetaryRep(repo: PlanetaryDataContract.Repository) = PlanetaryRepository(repo)

    @Provides
    @ListScope
    fun listrepo(
        local: PlanetaryDataContract.Local,
        remote: PlanetaryDataContract.Remote
    ): PlanetaryDataContract.Repository = PlanetaryRepoImpl(local, remote)


    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @ListScope
    fun remoteData(planetaryService: PlanetaryService): PlanetaryDataContract.Remote =
        PlanetaryRemoteData(planetaryService)

    @Provides
    @ListScope
    fun localData(planetaryDb: NasaDatabase): PlanetaryDataContract.Local =
        PlanetaryLocalData(planetaryDb)

    @Provides
    @ListScope
    fun planetaryDb(context: Context): NasaDatabase = NasaDatabase.getInstance(context)

    @Provides
    @ListScope
    fun postService(retrofit: Retrofit): PlanetaryService =
        retrofit.create(PlanetaryService::class.java)
}