package com.test.mvpdemo.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.test.mvpdemo.R
import com.test.mvpdemo.data.network.NetworkHandler
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.data.usecases.FetchDetailUsecase
import com.test.mvpdemo.ui.base.BaseActivity
import com.test.mvpdemo.ui.base.Response
import com.test.mvpdemo.ui.fragments.DetailFragment
import com.test.mvpdemo.ui.fragments.ErrorFragment
import com.test.mvpdemo.ui.fragments.OnRefreshListener
import com.test.mvpdemo.ui.presenter.MainPresenter
import com.test.mvpdemo.ui.presenter.MainView
import com.test.mvpdemo.util.SchedulersUtil
import com.test.mvpdemo.util.addDetailScreenAnimation
import com.test.mvpdemo.util.addErrorAnimation
import com.test.mvpdemo.util.getRotateAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter, MainView>(), ErrorFragment.OnRetryListener, com.test.mvpdemo.ui.presenter.MainView, OnRefreshListener {

    var onRefresh: Boolean = false
    lateinit var detailFrament: DetailFragment
    var title: String? = null

    override fun onRefresh() {
        onRefresh = true
        loadData()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter {
        view = this

        val apiService = NetworkHandler.getApiService()
        val usecase = FetchDetailUsecase(apiService)
        val schedulers = SchedulersUtil()
        return MainPresenter(schedulers, usecase)
    }

    fun init() {
        if (supportFragmentManager.findFragmentByTag("success") != null) {
            detailFrament = supportFragmentManager.findFragmentByTag("success") as DetailFragment
            supportFragmentManager.beginTransaction().replace(R.id.flContainer, detailFrament, "success").commit()
            onLoading(Response.OnLoading(false))
        } else {
            loadData()
        }
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("title", title)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        title = savedInstanceState?.getString("title")
        tvTitle.text = title
        toolbar.visibility = View.VISIBLE
    }

    override fun onRetryClick() {
        removeAllViews()
        loadData()
    }

    @SuppressLint("RestrictedApi")
    fun removeAllViews() {

        try {
            for (fragment in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        } catch (e: Exception) {

        }
    }

    fun loadData() {
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

                if (onRefresh)
                    return

                if (response.showLoading) {
                    startLoading()
                } else {
                    imvLoading.clearAnimation()
                    imvLoading.visibility = View.GONE
                }
            }
            is Response.SuccessResponse -> {
                val detailReponse = response.s as DetailResponse
                title = detailReponse.title
                tvTitle.text = detailReponse.title
                toolbar.visibility = View.VISIBLE


//                removeAllViews()
                if (onRefresh) {
                    detailFrament.addNewData(detailReponse.rows)
                } else {
                    detailFrament = DetailFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("details", detailReponse)
                    detailFrament.arguments = bundle
                    addDetailScreenAnimation(detailFrament)
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, detailFrament, "success").commit()
                }
                onRefresh = false
            }
            is Response.ErrorResponse -> {
                val fragment = ErrorFragment()
                addErrorAnimation(fragment)
                supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "error").commit()
            }

        }

    }


}
