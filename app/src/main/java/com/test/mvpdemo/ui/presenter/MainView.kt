package com.test.mvpdemo.ui.presenter

import com.test.mvpdemo.ui.base.BaseView
import com.test.mvpdemo.ui.base.Response

interface MainView : BaseView {

    fun onError(response : Response)
    fun onSuccess(response: Response)
    fun onLoading(response: Response)
}