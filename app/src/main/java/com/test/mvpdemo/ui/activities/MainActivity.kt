package com.test.mvpdemo.ui.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.test.mvpdemo.R
import com.test.mvpdemo.data.network.NetworkHandler
import com.test.mvpdemo.data.repositories.FeedRespository
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.data.usecases.FetchDetailUsecase
import com.test.mvpdemo.ui.base.BaseActivity
import com.test.mvpdemo.ui.base.Response
import com.test.mvpdemo.ui.fragments.DetailFragment
import com.test.mvpdemo.ui.fragments.ErrorFragment
import com.test.mvpdemo.ui.fragments.OnRefreshListener
import com.test.mvpdemo.ui.presenter.MainPresenter
import com.test.mvpdemo.ui.presenter.MainView
import com.test.mvpdemo.ui.viewmodel.MainActivityViewModel
import com.test.mvpdemo.util.SchedulersUtil
import com.test.mvpdemo.util.addDetailScreenAnimation
import com.test.mvpdemo.util.addErrorAnimation
import com.test.mvpdemo.util.getRotateAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter, MainView>(), ErrorFragment.OnRetryListener, com.test.mvpdemo.ui.presenter.MainView, OnRefreshListener {

    var onRefresh: Boolean = false
    lateinit var detailFrament: DetailFragment
    lateinit var viewModel: MainActivityViewModel
    var title: String? = null

    /**
     * load data on pull to refresh
     */
    override fun onRefresh() {
        onRefresh = true
        loadData()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    /**
     * initialize presenter
     */
    override fun initPresenter(): MainPresenter {
        view = this

        viewModel = ViewModelProviders.of(this@MainActivity).get(MainActivityViewModel::class.java)

        /**
         * Create new presenter or fetch if from the viewmodel if it has retained the object
         */

        val presenter: MainPresenter = viewModel.presenter ?: run {
            val apiService = NetworkHandler.getApiService()
            val feedsRepository = FeedRespository(apiService)
            val usecase = FetchDetailUsecase(feedsRepository)
            val schedulers = SchedulersUtil()
            presenter = MainPresenter(schedulers, usecase)
            /**
             * assign the viewmode to presenter
             */
            viewModel.presenter = presenter
            return presenter
        }

        return presenter
    }

    fun init() {
        loadData()
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

    /**
     * remove all fragments
     */
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

                /**
                 * if the pull to refresh is activated we dont show the loader on the screen
                 */
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

                /**
                 * pass the data to the fragment
                 */
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
            /**
             * on error load error fragment
             */
            is Response.ErrorResponse -> {
                val fragment = ErrorFragment()
                addErrorAnimation(fragment)
                supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "error").commit()
            }

        }

    }


}
