package com.test.mvpdemo.data.usecases

import com.test.mvpdemo.data.network.ApiService
import com.test.mvpdemo.data.response.AboutResponse
import io.reactivex.Observable

class FetchDetailUsecase constructor(var apiService: ApiService) : Interactor<AboutResponse> {
    override fun execute(): Observable<AboutResponse> {
        return apiService.getData()
    }
}