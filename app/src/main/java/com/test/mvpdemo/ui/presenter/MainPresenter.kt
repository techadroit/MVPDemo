package com.test.mvpdemo.ui.presenter

import com.test.mvpdemo.data.network.NetworkHandler
import com.test.mvpdemo.data.response.AboutResponse
import com.test.mvpdemo.data.usecases.FetchDetailUsecase
import com.test.mvpdemo.util.SchedulersUtil
import io.reactivex.Observer
import io.reactivex.observers.DisposableObserver
import test.gojek.gojektest.ui.base.BasePresenter

class MainPresenter : BasePresenter() {

    var schedulers = SchedulersUtil()

    fun loadData() {
        var apiService = NetworkHandler.getApiService()
        var usecase = FetchDetailUsecase(apiService)

        disposables.add(usecase.execute().subscribeOn(schedulers.io()).observeOn(schedulers.ui())
                .subscribeWith(object : DisposableObserver<AboutResponse>() {
                    override fun onComplete() {
                        print(" api completed successfully ")
                    }

                    override fun onNext(t: AboutResponse) {
                        print(" api on next successfully ")
                    }

                    override fun onError(e: Throwable) {
                        print(" api completed on error " + e.localizedMessage)
                    }

                })
        )
    }
}