package com.test.mvpdemo.data.repositories


import com.test.mvpdemo.data.network.ApiService
import com.test.mvpdemo.data.response.DetailResponse
import io.reactivex.Observable

/**
 * Remote repository to load feeds from the server and cache it locally
 */
class FeedRespository constructor(var apiService: ApiService) {

    var detailResponse: DetailResponse? = null

    /**
     *  Load the feeds from the server and cache it locally
     */
    fun getFeeds(): Observable<DetailResponse?> {

        /**
         * load the feeds from the cache if available
         */

        return detailResponse?.let {
            Observable.fromCallable { detailResponse }
        } ?: run {
            apiService.getData().doOnNext { detailResponse = it }
        }
    }
}