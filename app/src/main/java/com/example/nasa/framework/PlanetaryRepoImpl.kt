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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class PlanetaryRepoImpl(
    private val local: PlanetaryDataContract.Local,
    private val remote: PlanetaryDataContract.Remote
) : PlanetaryDataContract.Repository {

    private var hitRemote : Boolean =false

    override val postFetchOutcome: PublishSubject<Outcome<List<PlanetaryResponse>>> =
        PublishSubject.create<Outcome<List<PlanetaryResponse>>>()

    override fun getPictures() {
        postFetchOutcome.loading(true)
        remote.getPictures()
            .subscribeOn(Schedulers.io())
            .map { resp -> save(resp) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { error -> handleError(error) })
    }

    private fun save(resp: PlanetaryResponse) {
        local.savePlanetaryResponse(resp)
    }

    override fun handleError(error: Throwable) {
        postFetchOutcome.failed(error)
    }

    override fun fetchPictureFromDb() {
        postFetchOutcome.loading(true)
        //Observe changes to the db
        local.getPlanetaryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe({ pictures ->
                postFetchOutcome.success(pictures)
                hitRemote = true
                if (pictures.isEmpty())
                    getPictures()
                else {

                    val cal = Calendar.getInstance()
                    cal.add(Calendar.HOUR_OF_DAY,-5)
                    cal.add(Calendar.MINUTE,-30)

                    for (i in pictures.indices) {
                        if (pictures[i].date.equals(SimpleDateFormat("yyyy-MM-dd").format(cal.time))) {
                            hitRemote = false
                            break
                        }
                    }

                    if(hitRemote)
                        getPictures()
                }




            }, { error -> handleError(error) })

    }

    override fun saveResp(resp: PlanetaryResponse) {

    }
}