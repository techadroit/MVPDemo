package com.test.mvpdemo.ui.viewmodel

import com.test.mvpdemo.ui.base.BaseViewModel
import com.test.mvpdemo.ui.presenter.MainPresenter

class MainActivityViewModel : BaseViewModel() {

    /**
     * Assign presenter and retain it accross orientation changes
     */
    var presenter: MainPresenter ?= null
}