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
    val email: String,
    val photoUrl: String? = null,
)