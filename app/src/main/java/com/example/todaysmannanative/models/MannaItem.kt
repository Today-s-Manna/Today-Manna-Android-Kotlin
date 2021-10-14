package com.example.todaysmannanative.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MannaItem(
    var verse : String = "",
    var contents: ArrayList<String> = ArrayList()
    )
    : Parcelable