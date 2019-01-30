package com.test.mvpdemo.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.data.usecases.FetchDetailUsecase
import com.test.mvpdemo.ui.base.BaseViewModel
import com.test.mvpdemo.ui.base.Response
import com.test.mvpdemo.ui.base.SingleLiveEvent
import com.test.mvpdemo.util.addApiSchedulers
import io.reactivex.observers.DisposableObserver

class MainActivityViewModel : BaseViewModel() {

    var onLoadData = SingleLiveEvent<Response>()

    /**
     * load data from the server and update the view
     */
    fun loadData(usecase: FetchDetailUsecase) {
        showLoading(true)

        disposables.add(addApiSchedulers(usecase.execute())
                .subscribeWith(object : DisposableObserver<DetailResponse>() {
                    override fun onComplete() {}
                    override fun onNext(t: DetailResponse) {
                        showLoading(false)
                        onLoadData.value = Response.SuccessResponse(t)
                    }

                    override fun onError(e: Throwable) {
                        showLoading(false)
                        onLoadData.value = Response.ErrorResponse("")
                    }

                })
        )
    }

    fun showLoading(boolean: Boolean) {
        onLoadData.value = Response.OnLoading(boolean)
    }
}