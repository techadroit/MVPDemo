package com.test.mvpdemo.data.usecases

import com.test.mvpdemo.data.network.ApiService
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.ui.adapter.WITHOUT_DESC
import com.test.mvpdemo.util.isNull
import io.reactivex.Observable

class FetchDetailUsecase constructor(private var apiService: ApiService) : Interactor<DetailResponse> {
    override fun execute(): Observable<DetailResponse> {
        return apiService.getData()
                .map {
            val list = it.rows.toMutableList()
            val removeItemID = mutableListOf<Int>()
            for (i in 0..list.size - 1) {
                val item = list.get(i)
                if (isNull(item.title)) {
                    removeItemID.add(i)
                }

                if (isNull(item.description)) {
                    item.itemViewType = WITHOUT_DESC
                }
            }

            for (i in removeItemID) {
                list.removeAt(i)
            }

            it.rows = list

            it
        }
    }
}