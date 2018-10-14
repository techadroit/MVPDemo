package com.test.mvpdemo.ui.presenter

import com.test.mvpdemo.data.network.NetworkHandler
import com.test.mvpdemo.data.response.AboutResponse
import com.test.mvpdemo.data.usecases.FetchDetailUsecase
import com.test.mvpdemo.ui.base.BasePresenter
import com.test.mvpdemo.ui.base.Response
import com.test.mvpdemo.util.SchedulersUtil
import io.reactivex.observers.DisposableObserver

class MainPresenter : BasePresenter<MainView>() {

    var schedulers = SchedulersUtil()

    fun loadData() {
        var apiService = NetworkHandler.getApiService()
        var usecase = FetchDetailUsecase(apiService)
        showLoading(true)

        disposables.add(usecase.execute().subscribeOn(schedulers.io()).observeOn(schedulers.ui())
                .subscribeWith(object : DisposableObserver<AboutResponse>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: AboutResponse) {
                        showLoading(false)
                        view?.onSuccess(Response.SuccessResponse(t))
                    }

                    override fun onError(e: Throwable) {
                        showLoading(false)
                        view?.onError(Response.ErrorResponse(""))
                    }

                })
        )
    }

    fun showLoading(boolean : Boolean){
        view?.onLoading(Response.OnLoading(boolean))
    }
}