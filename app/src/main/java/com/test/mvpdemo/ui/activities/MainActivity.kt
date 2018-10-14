package com.test.mvpdemo.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.mvpdemo.R
import com.test.mvpdemo.ui.presenter.MainPresenter
import com.test.mvpdemo.util.getRotateAnimation
import kotlinx.android.synthetic.main.activity_main.*
import test.gojek.gojektest.ui.base.BaseActivity

class MainActivity : BaseActivity<MainPresenter>() {

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startLoading()
        presenter.loadData()
    }

    fun startLoading() {
        imvLoading.visibility = View.VISIBLE
        imvLoading.startAnimation(getRotateAnimation())
    }
}
