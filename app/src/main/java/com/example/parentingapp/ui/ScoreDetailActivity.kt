package com.example.parentingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parentingapp.databinding.ActivityScoreDetailBinding

class ScoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}