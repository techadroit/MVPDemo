package com.test.mvpdemo

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.test.mvpdemo.ui.activities.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


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

//    @Test fun checkDataLoaded(){
//
//        val idlingResources = DataLoadIdlingResources(activity)
//        Espresso.registerIdlingResources(idlingResources)
//        Espresso.onView(withId(R.id.rvDetailList)).check(matches(isDisplayed()))
//    }

}

