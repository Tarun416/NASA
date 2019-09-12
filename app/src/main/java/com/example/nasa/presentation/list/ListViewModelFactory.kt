package com.example.nasa.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.interactors.GetPictures
import com.example.nasa.framework.PlanetaryDataRepository
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val interactors: GetPictures, private val compositeDisposable: CompositeDisposable,private val planetarydataRepository  : PlanetaryDataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(interactors, compositeDisposable , planetarydataRepository) as T
    }
}