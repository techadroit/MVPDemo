package com.test.mvpdemo.data.response

import android.os.Parcelable
import com.test.mvpdemo.ui.adapter.WITH_DESC
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail(
        val title: String ?,
        val description: String ?,
        val imageHref : String ?
) : Parcelable{

    var itemViewType : Int = WITH_DESC

}