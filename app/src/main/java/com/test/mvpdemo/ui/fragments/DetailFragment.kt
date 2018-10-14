package com.test.mvpdemo.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mvpdemo.R
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.ui.adapter.DetailListAdapter
import com.test.mvpdemo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.detail_fragment_layout.*

class DetailFragment : BaseFragment() {

    lateinit var detailResponse : DetailResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.detail_fragment_layout,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDataToView()
    }

    fun addDataToView(){
        var adapter = DetailListAdapter()
        detailResponse = arguments?.get("details") as DetailResponse
        adapter.list =  detailResponse.rows
        rvDetailList.adapter = adapter
        rvDetailList.layoutManager = LinearLayoutManager(context)
    }

}