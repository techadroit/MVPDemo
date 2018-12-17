package com.test.mvpdemo

import android.content.Intent
import android.app.Activity
import android.support.test.rule.ActivityTestRule
import android.util.Log


open class MainActivityTestRule<A : Activity>(activityClass: Class<A>) : ActivityTestRule<A>(activityClass) {

    override fun beforeActivityLaunched() {
        Log.e("MainActivityTestRule", "Execute before the activity is launched")
        super.beforeActivityLaunched()
    }

    override fun afterActivityLaunched() {
        Log.e("MainActivityTestRule", "Execute after the activity has been launched")
        super.afterActivityLaunched()
    }

    override  fun afterActivityFinished() {
        Log.e("MainActivityTestRule", "Cleanup after it has finished")
        super.afterActivityFinished()
    }

}