package com.test.mvpdemo

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.test.mvpdemo.ui.activities.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest {

    @get:Rule
    var mActivityRule = MainActivityTestRule(MainActivity::class.java)
    lateinit var activity: MainActivity

    @Before
    fun init() {
        activity = mActivityRule.activity
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun checkErrorFragmentAdded() {

        activity.onError(com.test.mvpdemo.ui.base.Response.ErrorResponse(""))
    }

}

