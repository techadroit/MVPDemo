package com.test.mvpdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import com.test.mvpdemo.data.response.Detail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.detail_row.*

class DetailViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View?
        get() = itemView

    fun bind(detail: Detail) {

        tvTitle.text = detail.title
        tvDescription.text = detail.description
        Picasso.get().load(detail.imageHref).into(imvNews);
    }
}