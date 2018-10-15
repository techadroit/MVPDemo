package com.test.mvpdemo

import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.test.mvpdemo.ui.activities.MainActivity
import okhttp3.Response
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before



@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest {

    @get:Rule
    var mActivityRule = MainActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {

    }

    @Test fun checkErrorFragmentAdded(){
        var activity = mActivityRule.activity
        activity.processResponse(com.test.mvpdemo.ui.base.Response.ErrorResponse(""))
    }

    @Test fun checkListAdded(){
        var activity = mActivityRule.activity
        activity.processResponse(com.test.mvpdemo.ui.base.Response.SuccessResponse(""))
    }


}

