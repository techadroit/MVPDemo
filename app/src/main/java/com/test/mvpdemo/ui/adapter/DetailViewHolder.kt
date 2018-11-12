package com.test.mvpdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import com.test.mvpdemo.R
import com.test.mvpdemo.data.response.Detail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.detail_row.*

class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View?
        get() = itemView

    fun bind(detail: Detail) {

        tvTitle.text = detail.title

        if(tvDescription != null) {
            if (detail.description != null && !detail.description.equals("null", true)) {
                tvDescription.text = detail.description
                tvDescription.visibility = View.VISIBLE
            } else {
                tvDescription.visibility = View.GONE
            }
        }

        if (detail.imageHref != null && !detail.imageHref.equals("null", true)) {
            Picasso.get().load(detail.imageHref).placeholder(R.drawable.thumbnail).into(imvNews)
            imvNews.visibility = View.VISIBLE
        } else {
            imvNews.visibility = View.GONE
        }

    }
}