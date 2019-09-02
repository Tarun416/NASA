package com.example.nasa.framework

import com.example.core.data.PlanetaryDataContract
import com.example.core.data.remote.PlanetaryService
import com.example.core.domain.PlanetaryResponse
import io.reactivex.Single

class PlanetaryRemoteData(private val plantaryService : PlanetaryService) : PlanetaryDataContract.Remote {

    override fun getPictures(): Single<PlanetaryResponse>  = plantaryService.getPictures("DeIZgYI0ez4OTeUwfJinCe1aM1gmCwNOTzwVOZw4")

    /*@Inject
    lateinit var remote : PlanetaryService

    override fun getPictures(apiKey: String)=
          remote.getPictures(apiKey)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(object  : SingleObserver<PlanetaryResponse>{
                  override fun onSuccess(t: PlanetaryResponse) {

                  }

                  override fun onSubscribe(d: Disposable) {

                  }

                  override fun onError(e: Throwable) {

                  }

              })*/

    }

