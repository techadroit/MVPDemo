package com.test.mvpdemo.ui.viewmodel

import com.test.mvpdemo.ui.base.BaseViewModel
import com.test.mvpdemo.ui.presenter.MainPresenter

class MainActivityViewModel : BaseViewModel() {

    var presenter: MainPresenter ?= null
}