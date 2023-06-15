package com.example.parentingapp.data

import java.io.Serializable

data class Contact(
    val UID: String = "",
//    val Nama:String = "",
    val email: String = "",
    val name: String = "",
    val DbnNyR8dDdZXUEkQugGcyQfXpHs1: HashMap<String, String>? = null,
    val MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2: HashMap<String, String>? = null,
    var rooms: MutableMap<String, Any?>? = null
) : Serializable
