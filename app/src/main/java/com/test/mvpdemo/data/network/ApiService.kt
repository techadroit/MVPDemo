package com.test.mvpdemo.data.network

import com.test.mvpdemo.data.response.AboutResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getData() : Observable<AboutResponse>
}