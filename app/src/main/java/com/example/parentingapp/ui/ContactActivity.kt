package com.example.parentingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parentingapp.databinding.ActivityContactBinding
import com.example.parentingapp.databinding.ActivityScoreDetailBinding
import com.google.firebase.ktx.Firebase

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}