package com.example.nasa.framework

import com.example.core.data.PlanetaryDataContract
import com.example.core.domain.PlanetaryResponse
import com.example.core.networking.Outcome
import com.example.nasa.framework.extensions.failed
import com.example.nasa.framework.extensions.loading
import com.example.nasa.framework.extensions.success
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class PlanetaryRepoImpl (private val remote : PlanetaryDataContract.Remote) : PlanetaryDataContract.Repository {
    override val postFetchOutcome: PublishSubject<Outcome<PlanetaryResponse>> = PublishSubject.create<Outcome<PlanetaryResponse>>()

    override fun getPictures() {
       postFetchOutcome.loading(true)
       remote.getPictures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({pictures -> postFetchOutcome.success(pictures)},{ error -> handleError(error)})

    }

    override fun handleError(error: Throwable) {
        postFetchOutcome.failed(error)
    }
}