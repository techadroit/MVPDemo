package com.test.mvpdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AboutResponse(
        val title: String,
        val rows: List<Detail>
) : Parcelable