package com.test.mvpdemo

import com.test.mvpdemo.data.network.ApiService
import com.test.mvpdemo.data.network.NetworkHandler
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.data.usecases.FetchDetailUsecase
import com.test.mvpdemo.ui.base.Response
import com.test.mvpdemo.ui.presenter.MainPresenter
import com.test.mvpdemo.ui.presenter.MainView
import com.test.mvpdemo.util.SchedulersUtil
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock lateinit var usecase: FetchDetailUsecase
    @Mock lateinit var mainView: MainView
    @InjectMocks lateinit var mainPrsenter : MainPresenter

    @Before @Throws fun setUp(){
        NetworkHandler.init()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler({ Schedulers.trampoline()})
        MockitoAnnotations.initMocks(this)
        mainPrsenter = MainPresenter(SchedulersUtil(), usecase)
        mainPrsenter.view = mainView
    }

    @Test fun checkLoadData(){

        val detailResponse =  mock(DetailResponse::class.java)
        `when`(usecase.execute()).thenReturn(Observable.just(detailResponse))
        mainPrsenter.loadData()
        verify(mainView).onSuccess(Response.SuccessResponse(detailResponse))
    }

    @Test fun checkDataFailure(){

        `when`(usecase.execute()).thenReturn(Observable.error(IOException()))
        mainPrsenter.loadData()
        verify(mainView).onError(Response.ErrorResponse(""))
    }

}