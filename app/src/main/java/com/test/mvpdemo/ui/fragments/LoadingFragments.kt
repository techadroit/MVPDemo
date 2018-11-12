package com.test.mvpdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mvpdemo.R
import com.test.mvpdemo.ui.base.BaseFragment
import com.test.mvpdemo.util.getRotateAnimation
import kotlinx.android.synthetic.main.fragment_loading_layout.*

class LoadingFragments : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_loading_layout,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imvLoading.visibility = View.VISIBLE
        imvLoading.startAnimation(getRotateAnimation())
    }
}