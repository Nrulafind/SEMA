package com.example.parentingapp.data

import java.io.Serializable

data class Contact(
    val UID: String = "",
    val nama:String = "",
    var rooms: MutableMap<String, Any?>? = null
) : Serializable
