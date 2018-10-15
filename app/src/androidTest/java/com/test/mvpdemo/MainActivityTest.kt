package com.test.mvpdemo

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
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
    lateinit var activity : MainActivity

    @Before
    fun init() {
        activity = mActivityRule.activity

    }


    @Test fun checkErrorFragmentAdded(){

        activity.onError(com.test.mvpdemo.ui.base.Response.ErrorResponse(""))
    }

    @Test fun checkDetailFragmentLoaded(){

       Espresso.onView(withId(R.id.rvDetailList)).check(matches(isDisplayed()))
    }

    @Test fun retryButtonclickTest(){

        activity.onError(com.test.mvpdemo.ui.base.Response.ErrorResponse(""))
        Espresso.onView(withId(R.id.btnRetry)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.btnRetry)).perform(click())
    }

}

