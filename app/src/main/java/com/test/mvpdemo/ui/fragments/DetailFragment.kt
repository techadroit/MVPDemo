package com.test.mvpdemo.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mvpdemo.R
import com.test.mvpdemo.data.response.Detail
import com.test.mvpdemo.data.response.DetailResponse
import com.test.mvpdemo.ui.adapter.DetailListAdapter
import com.test.mvpdemo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.detail_fragment_layout.*

class DetailFragment : BaseFragment() {

    lateinit var detailResponse : DetailResponse
    var refreshListener: OnRefreshListener ?= null
    var adapter = DetailListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment_layout,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDataToView()
        addSwipeListener()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnRefreshListener)
            refreshListener = context
    }

    fun addDataToView(){

        detailResponse = arguments?.get("details") as DetailResponse
        adapter.list =  detailResponse.rows
        rvDetailList.adapter = adapter
        rvDetailList.layoutManager = LinearLayoutManager(context)
    }

    fun addSwipeListener(){

        swipeRefresh.setOnRefreshListener {
            refreshListener?.onRefresh()
        }
    }

    fun addNewData(list : List<Detail>){
        adapter.list.clear()
        adapter.list.addAll(list)
        adapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

}


interface OnRefreshListener{

    fun onRefresh()
}