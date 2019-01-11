package com.test.mvpdemo.ui.presenter

import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.data.usecases.FetchDetailUsecase
import com.test.mvpdemo.ui.base.BasePresenter
import com.test.mvpdemo.ui.base.Response
import com.test.mvpdemo.util.SchedulersUtil
import io.reactivex.observers.DisposableObserver

class MainPresenter(var schedulers: SchedulersUtil,
                    var usecase: FetchDetailUsecase
) : BasePresenter<MainView>() {


    /**
     * load data from the server and update the view
     */
    fun loadData() {
        showLoading(true)

        disposables.add(usecase.execute().subscribeOn(schedulers.io()).observeOn(schedulers.ui())
                .subscribeWith(object : DisposableObserver<DetailResponse>() {
                    override fun onComplete() {}
                    override fun onNext(t: DetailResponse) {
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

    fun showLoading(boolean: Boolean) {
        view?.onLoading(Response.OnLoading(boolean))
    }
}