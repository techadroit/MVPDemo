package com.test.mvpdemo.util

import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.test.mvpdemo.ui.fragments.DetailFragment
import com.test.mvpdemo.ui.fragments.ErrorFragment

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

fun addErrorAnimation(fragment: ErrorFragment) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        fragment.enterTransition = Slide(Gravity.BOTTOM)
        fragment.exitTransition = Slide(Gravity.BOTTOM)
    }
}

fun addDetailScreenAnimation(fragment: DetailFragment) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        fragment.enterTransition = Fade()
    }
}