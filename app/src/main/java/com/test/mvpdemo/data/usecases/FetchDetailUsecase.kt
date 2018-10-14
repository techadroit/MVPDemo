package com.test.mvpdemo.data.usecases

import com.test.mvpdemo.data.network.ApiService
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.util.isNull
import io.reactivex.Observable

class FetchDetailUsecase constructor(var apiService: ApiService) : Interactor<DetailResponse> {
    override fun execute(): Observable<DetailResponse> {
        return apiService.getData().filter {
            !isNull(it.title)
        }
    }
}