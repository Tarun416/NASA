package com.example.nasa.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.PlanetaryDataContract
import com.example.core.domain.PlanetaryResponse
import com.example.core.interactors.GetPictures
import com.example.core.networking.Outcome
import com.example.nasa.framework.PostDH
import com.example.nasa.framework.extensions.toLiveData
import io.reactivex.disposables.CompositeDisposable

class ListViewModel(
    private val interactors: GetPictures,
    private val compositeDisposable: CompositeDisposable,
    private val repo : PlanetaryDataContract.Repository
) : ViewModel() {

    val postsOutcome: LiveData<Outcome<PlanetaryResponse>> by lazy {
        //Convert publish subject to livedata
        repo.postFetchOutcome.toLiveData(compositeDisposable)
    }

    fun fetchPictures()
    {
        if(postsOutcome!=null)
        interactors.invoke()
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
        PostDH.destroyListComponent()
    }

}