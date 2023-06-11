package com.example.parentingapp.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class StudentResponse(

	@field:SerializedName("studentID")
	val studentID: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("angkatan")
	val angkatan: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable
