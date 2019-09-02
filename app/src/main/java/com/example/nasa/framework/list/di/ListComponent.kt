package com.example.nasa.framework.list.di

import androidx.fragment.app.ListFragment
import com.example.core.data.PlanetaryDataContract
import com.example.core.data.PlanetaryRepository
import com.example.core.data.remote.PlanetaryService
import com.example.core.interactors.GetPictures
import com.example.nasa.framework.PlanetaryRemoteData
import com.example.nasa.framework.PlanetaryRepoImpl
import com.example.nasa.framework.di.CoreComponent
import com.example.nasa.presentation.list.ListViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@ListScope
@Component(dependencies = [CoreComponent::class], modules = [ListModule::class])
interface ListComponent
{
    fun planetaryService() : PlanetaryService
    fun inject( fragment :com.example.nasa.presentation.list.ListFragment)
}


@Module
@ListScope
class ListModule()
{
    /*ViewModel*/
    @Provides
    @ListScope
    fun listViewModelFactory(interactors: GetPictures,compositeDisposable: CompositeDisposable, repo : PlanetaryDataContract.Repository)= ListViewModelFactory(interactors,compositeDisposable,repo)

    @Provides
    @ListScope
    fun interactors(planetaryRepo : PlanetaryRepository) = GetPictures(planetaryRepo)

    @Provides
    @ListScope
    fun planetaryRep(repo : PlanetaryDataContract.Repository) = PlanetaryRepository(repo)

    @Provides
    @ListScope
    fun listrepo(remote : PlanetaryDataContract.Remote) :PlanetaryDataContract.Repository= PlanetaryRepoImpl(remote)




    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @ListScope
    fun remoteData(planetaryService : PlanetaryService): PlanetaryDataContract.Remote = PlanetaryRemoteData(planetaryService)

     @Provides
     @ListScope
     fun postService(retrofit: Retrofit): PlanetaryService = retrofit.create(PlanetaryService::class.java)
}