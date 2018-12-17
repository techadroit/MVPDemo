package com.test.mvpdemo.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mvpdemo.R
import com.test.mvpdemo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.error_screen.*

class ErrorFragment : BaseFragment() {

    private var listener: OnRetryListener? = null

    interface OnRetryListener {
        fun onRetryClick()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.error_screen, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnRetryListener)
            listener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addRetryListener()
    }

    fun addRetryListener() {
        btnRetry.setOnClickListener {
            listener?.onRetryClick()
        }
    }

}