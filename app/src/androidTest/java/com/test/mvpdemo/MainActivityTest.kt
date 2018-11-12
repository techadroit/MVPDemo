package com.test.mvpdemo

import android.os.Bundle
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import com.test.mvpdemo.data.response.Detail
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.ui.activities.MainActivity
import com.test.mvpdemo.ui.fragments.DetailFragment
import com.test.mvpdemo.ui.fragments.ErrorFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
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

    @Test
    fun retryButtonclickTest() {

        startFragment(ErrorFragment())
        Espresso.onView(withId(R.id.btnRetry)).perform(click())
    }

    fun startFragment(fragment: Fragment) {
        val fragmentManager = activity.getSupportFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(fragment, "error")
        fragmentTransaction.commit()
    }

//    @Test fun checkDataLoaded(){
//
//        val idlingResources = DataLoadIdlingResources(activity)
//        Espresso.registerIdlingResources(idlingResources)
//        Espresso.onView(withId(R.id.rvDetailList)).check(matches(isDisplayed()))
//    }

}

