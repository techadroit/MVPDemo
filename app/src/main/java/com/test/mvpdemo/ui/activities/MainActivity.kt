package com.test.mvpdemo.ui.activities

import android.os.Bundle
import android.view.View
import com.test.mvpdemo.R
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.ui.base.BaseActivity
import com.test.mvpdemo.ui.base.Response
import com.test.mvpdemo.ui.fragments.DetailFragment
import com.test.mvpdemo.ui.fragments.ErrorFragment
import com.test.mvpdemo.ui.presenter.MainPresenter
import com.test.mvpdemo.ui.presenter.MainView
import com.test.mvpdemo.util.addDetailScreenAnimation
import com.test.mvpdemo.util.addErrorAnimation
import com.test.mvpdemo.util.getRotateAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter,MainView>(), ErrorFragment.OnRetryListener, com.test.mvpdemo.ui.presenter.MainView {


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter {
        view = this
        return MainPresenter()
    }

    override fun onStart() {
        super.onStart()

        presenter.loadData()
    }

    override fun onRetryClick() {
        for (fragment in supportFragmentManager.fragments) {
            fragment?.let {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }

        fetchWeatherInfo()
    }

    fun fetchWeatherInfo() {
        presenter.loadData()
    }

    fun startLoading() {
        imvLoading.visibility = View.VISIBLE
        imvLoading.startAnimation(getRotateAnimation())
    }

    override fun onError(response: Response) {
        processResponse(response)
    }

    override fun onSuccess(response: Response) {
        processResponse(response)
    }

    override fun onLoading(response: Response) {
        processResponse(response)
    }


    fun processResponse(response: Response) {

        when (response) {

            is Response.OnLoading -> {
                if (response.showLoading) {
                    startLoading()
                } else {
                    imvLoading.clearAnimation()
                    imvLoading.visibility = View.GONE
                }
            }
            is Response.SuccessResponse -> {
                var detailReponse = response.s as DetailResponse
                tvTitle.text = detailReponse.title
                toolbar.visibility = View.VISIBLE
                var fragment = DetailFragment()
                var bundle = Bundle()
                bundle.putParcelable("details", detailReponse)
                fragment.arguments = bundle
                addDetailScreenAnimation(fragment)
                supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "success").commit()
            }
            is Response.ErrorResponse -> {
                var fragment = ErrorFragment()
                addErrorAnimation(fragment)
                supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "error").commit()

            }

        }

    }
}
