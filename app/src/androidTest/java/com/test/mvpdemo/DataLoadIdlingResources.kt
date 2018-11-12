package com.test.mvpdemo

import android.support.test.espresso.IdlingResource
import com.test.mvpdemo.ui.activities.MainActivity


class DataLoadIdlingResources(var activity: MainActivity) : IdlingResource {

    lateinit var recourceCallback: IdlingResource.ResourceCallback
    private var isIdle: Boolean = false

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {

        if (isIdle) return true

        if(activity.fragmentManager.findFragmentByTag("success") != null){
            isIdle = true
        }

        if (isIdle) {
            recourceCallback.onTransitionToIdle()
        }
        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.recourceCallback = callback
    }


}