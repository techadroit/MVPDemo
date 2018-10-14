package com.test.mvpdemo.util

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

fun getRotateAnimation(): Animation {

    val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
    )
    rotate.duration = 700
    rotate.repeatCount = Animation.INFINITE
    rotate.interpolator = LinearInterpolator()

    return rotate
}