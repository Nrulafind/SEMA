package com.example.parentingapp.data

data class User(
    val id: String,
    val name: String,
    val photoUrl: String? = null,
    val grade: String,
    val semester: String,
    val status: String,
)

val dummyUser = User(
    "2023669123",
    "Jung Hoseok",
    "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/12/J-Hope-Jung-Ho-Seok.jpg",
    "11-A",
    "Semester Genap",
    "Outstanding Student"
)