package com.example.nasa.framework

import com.example.core.data.local.PlanetaryLocal
import com.example.core.data.remote.PlanetaryRemote
import com.example.core.domain.PlanetaryResponse
import com.example.core.domain.repository.PlanetaryRepository
import com.example.core.networking.Outcome
import com.example.nasa.framework.extensions.failed
import com.example.nasa.framework.extensions.loading
import com.example.nasa.framework.extensions.success
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.text.SimpleDateFormat
import java.util.*

class PlanetaryDataRepository(val remote: PlanetaryRemote, val local: PlanetaryLocal) :
    PlanetaryRepository {
    private var hitRemote: Boolean = false
    override val postFetchOutcome: PublishSubject<Outcome<List<PlanetaryResponse>>> =
        PublishSubject.create()

    override fun getPictureFromDb() {
        postFetchOutcome.loading(true)
        local.getPicturesFromDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pictures ->
                postFetchOutcome.success(pictures)
                if (pictures.isEmpty())
                    getPictureFromServer()
                else
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
        remote.getPictures("JfQ2g8NWy92kBwRU34FmvkHKK6GFmUHEyQUqBue4")
            .subscribeOn(Schedulers.io())
            .map { resp -> local.savePictureIntoDb(resp) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { error -> handleError(error) })
    }

    private fun handleError(error: Throwable?) {
        postFetchOutcome.failed(error!!)
    }


}
