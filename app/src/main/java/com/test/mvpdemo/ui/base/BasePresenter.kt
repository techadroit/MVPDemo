package com.test.mvpdemo.ui.base


import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter< T : BaseView>  {

    val disposables = CompositeDisposable()
    var view : T ?= null

    fun attachView(view : T) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    /**
     * clear if any observable is running
     */
    fun onPresenterDestroy() {
        disposables.clear()
    }
}