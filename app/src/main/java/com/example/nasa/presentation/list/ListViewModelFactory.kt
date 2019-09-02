package com.example.nasa.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.data.PlanetaryDataContract
import com.example.core.interactors.GetPictures
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val interactors: GetPictures, private val compositeDisposable: CompositeDisposable , private val repo : PlanetaryDataContract.Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(interactors, compositeDisposable, repo) as T
    }
}