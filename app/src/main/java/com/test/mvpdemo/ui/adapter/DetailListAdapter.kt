package com.test.mvpdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.mvpdemo.R
import com.test.mvpdemo.data.response.Detail

val WITH_DESC = 0
val WITHOUT_DESC = 1

class DetailListAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    var list = mutableListOf<Detail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {

        val holder : RecyclerView.ViewHolder

        when(viewType) {
            WITH_DESC -> holder  = DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_row, parent, false))
            else -> holder =  DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_row_no_desc, parent, false))
        }
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list.get(position).itemViewType
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
}