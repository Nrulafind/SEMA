package com.example.parentingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parentingapp.data.News
import com.example.parentingapp.data.Notification
import com.example.parentingapp.databinding.ActivityDetailBinding
import com.example.parentingapp.databinding.ActivityNewsDetailBinding

@Suppress("DEPRECATION")
class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val news = intent.getParcelableExtra<News>(EXTRA_NEWS) as News

        binding.title.text = news.title
        binding.description.text = news.description
        binding.imageView2.setImageResource(news.image)


    }

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }
}