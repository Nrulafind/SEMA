package com.example.parentingapp.data

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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