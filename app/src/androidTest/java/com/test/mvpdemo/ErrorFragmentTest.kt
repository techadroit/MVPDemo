package com.test.mvpdemo

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import com.test.mvpdemo.ui.activities.MainActivity
import com.test.mvpdemo.ui.fragments.ErrorFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
public class ErrorFragmentTest {

    @get:Rule
    var mActivityRule = FragmentTestRule(ErrorFragment::class.java)

    @Test
    fun retryButtonclickTest() {
        mActivityRule.launchActivity(null)
        Espresso.onView(ViewMatchers.withId(R.id.btnRetry)).perform(ViewActions.click())
    }
}