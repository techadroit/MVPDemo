package com.test.mvpdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailResponse(
        val title: String,
        var rows: MutableList<Detail>
) : Parcelable