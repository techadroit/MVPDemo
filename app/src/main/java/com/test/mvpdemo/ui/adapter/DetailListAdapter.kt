package com.test.mvpdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.mvpdemo.R
import com.test.mvpdemo.data.response.Detail

class DetailListAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    var list = listOf<Detail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_row,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
}