package com.test.mvpdemo.data.usecases

import com.test.mvpdemo.data.repositories.FeedRespository
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.ui.adapter.WITHOUT_DESC
import com.test.mvpdemo.util.isNull
import io.reactivex.Observable

/**
 * Usecase to fetch feeds and assign different view types depending on the availability of the data like image, desc, etc.
 */
class FetchDetailUsecase constructor(private var feedsRepository: FeedRespository) : Interactor<DetailResponse> {
    override fun execute(): Observable<DetailResponse> {
        return feedsRepository.getFeeds()
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