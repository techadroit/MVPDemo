package com.test.mvpdemo.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    /**
     * clear if any observable is running
     */
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}