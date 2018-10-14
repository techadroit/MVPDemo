package com.test.mvpdemo.ui.base


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.mvpdemo.ui.base.BaseView

abstract class BaseActivity< p : BasePresenter<V>, V : BaseView> : AppCompatActivity() {

    lateinit protected var presenter: p


    /**
     * get the layout of the activity
     *
     * @return
     */
    protected abstract fun getLayout(): Int

    /**
     * initialize the presenter
     *
     * @return
     */
    protected abstract fun initPresenter(): p

    var view : V ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        presenter = initPresenter()

    }

    override fun onStart() {
        super.onStart()
        view?.let { presenter.attachView(view as V) }
    }

    override fun onStop() {
        super.onStop()
        view?.let { presenter.detachView() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onPresenterDestroy()
    }
}