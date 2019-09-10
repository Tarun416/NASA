package com.example.nasa.presentation.list

import androidx.lifecycle.ViewModel
import com.example.core.domain.PlanetaryResponse
import com.example.core.interactors.GetPictures
import com.example.core.networking.Outcome
import com.example.nasa.framework.PostDH
import com.example.nasa.framework.extensions.failed
import com.example.nasa.framework.extensions.loading
import com.example.nasa.framework.extensions.success
import com.example.nasa.framework.extensions.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.text.SimpleDateFormat
import java.util.*

class ListViewModel(
    private val interactors: GetPictures,
    private val compositeDisposable: CompositeDisposable


) : ViewModel() {

    private var hitRemote: Boolean = false
    val postFetchOutcome =
        PublishSubject.create<Outcome<List<PlanetaryResponse>>>()
    val postsOutcome by lazy { postFetchOutcome.toLiveData(compositeDisposable) }


    fun fetchPictures() {
        postFetchOutcome.loading(true)
        interactors.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pictures ->
                postFetchOutcome.success(pictures)
                if (pictures.isEmpty())
                    getPictureFromServer()
                else if(!hitRemote)
                    checkWhetherPictureAlredyFetched(pictures)

            }, { error -> handleError(error) })
    }

    private fun checkWhetherPictureAlredyFetched(pictures: List<PlanetaryResponse>) {
        hitRemote = true
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.HOUR_OF_DAY, -5)
        currentDate.add(Calendar.MINUTE, -30)

        for (i in pictures.indices) {
            if (pictures[i].date.equals(SimpleDateFormat("yyyy-MM-dd").format(currentDate.time))) {
                hitRemote = false
                break
            }
        }

        if (hitRemote)
            getPictureFromServer()
    }

    private fun getPictureFromServer() {
        postFetchOutcome.loading(true)
        interactors.invoke("JfQ2g8NWy92kBwRU34FmvkHKK6GFmUHEyQUqBue4")
            .subscribeOn(Schedulers.io())
            .map { resp -> interactors.invoke(resp) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { error -> handleError(error) })
    }

    private fun handleError(error: Throwable?) {
        postFetchOutcome.failed(error!!)
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
        PostDH.destroyListComponent()
    }

}