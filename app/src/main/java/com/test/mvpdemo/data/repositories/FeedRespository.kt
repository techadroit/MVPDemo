package com.test.mvpdemo.data.repositories


import com.test.mvpdemo.data.network.ApiService
import com.test.mvpdemo.data.response.DetailResponse
import io.reactivex.Observable

class FeedRespository constructor(var apiService: ApiService) {

    var detailResponse : DetailResponse ?= null

    fun getFeeds() : Observable<DetailResponse?> {
//        detailResponse?.let {
//            return apiService.getData().doOnNext { detailResponse = it }
//        }

        return detailResponse?.let { Observable.fromCallable { detailResponse } } ?:
        apiService.getData().doOnNext { detailResponse = it }
    }
}