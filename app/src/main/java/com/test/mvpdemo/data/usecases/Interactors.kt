package com.test.mvpdemo.data.usecases

import io.reactivex.Observable

interface Interactor<T> {

    fun execute() : Observable<T>
}