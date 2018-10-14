package test.gojek.gojektest.ui.base


import com.test.mvpdemo.ui.base.BaseView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter  {

    val disposables = CompositeDisposable()
    var view : BaseView ?= null

    fun attachView(view : BaseView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun onPresenterDestroy() {
        disposables.clear()
    }
}