package com.example.parentingapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val image: Int,
    val title: String,
    val description: String
) : Parcelable
