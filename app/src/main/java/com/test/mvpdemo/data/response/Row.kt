package com.test.mvpdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Row(
        val title: String,
        val description: String,
        val imageHref: String
) : Parcelable