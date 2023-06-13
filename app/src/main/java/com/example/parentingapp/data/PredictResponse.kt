package com.example.parentingapp.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PredictResponse(

	@field:SerializedName("results")
	val results: Double,

) : Parcelable


data class Input(
	val input_data: MutableList<Double>,
)