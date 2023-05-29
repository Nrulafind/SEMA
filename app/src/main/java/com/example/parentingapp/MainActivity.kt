package com.example.parentingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parentingapp.databinding.ActivityMainBinding
import com.example.parentingapp.ui.ChatDetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this@MainActivity, ScoreActivity::class.java)
        startActivity(intent)
    }
}