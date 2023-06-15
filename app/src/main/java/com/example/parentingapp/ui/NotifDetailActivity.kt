package com.example.parentingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parentingapp.data.Notification
import com.example.parentingapp.databinding.ActivityDetailBinding

@Suppress("DEPRECATION")
class NotifDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notif = intent.getParcelableExtra<Notification>(EXTRA_NOTIF) as Notification

        binding.title.text = notif.title
        binding.description.text = notif.description
        binding.imageView2.setImageResource(notif.image!!)


    }

    companion object {
        const val EXTRA_NOTIF = "extra_notif"
    }
}