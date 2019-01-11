package com.test.mvpdemo.ui.activities

import android.widget.FrameLayout
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.support.annotation.VisibleForTesting
import com.test.mvpdemo.R

/**
 *  A test activity to load fragments and test
 */

@VisibleForTesting
class TestActivity : AppCompatActivity() {
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frameLayout = FrameLayout(this)
        frameLayout.id = R.id.flContainer
        setContentView(frameLayout)
    }
}