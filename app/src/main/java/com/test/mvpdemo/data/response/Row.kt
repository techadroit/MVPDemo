package com.test.mvpdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail(
        val title: String ?,
        val description: String ?,
        val imageHref : String ?
) : Parcelable