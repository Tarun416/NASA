package com.example.nasa.presentation.list

import androidx.lifecycle.ViewModel
import com.example.core.interactors.GetPictures
import com.example.nasa.framework.PlanetaryDataRepository
import com.example.nasa.framework.PostDH
import com.example.nasa.framework.extensions.toLiveData
import io.reactivex.disposables.CompositeDisposable

class ListViewModel(
    private val interactors: GetPictures,
    private val compositeDisposable: CompositeDisposable,
    private val repository: PlanetaryDataRepository


) : ViewModel() {

    val postsOutcome by lazy { repository.postFetchOutcome.toLiveData(compositeDisposable) }


    fun fetchPictures() {

        if (postsOutcome != null)
            interactors.invoke()

    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
        PostDH.destroyListComponent()
    }

}