package com.example.parentingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.R
import com.example.parentingapp.adapter.NotificationAdapter
import com.example.parentingapp.data.Notification
import com.example.parentingapp.databinding.ActivityNotificationBinding

@Suppress("DEPRECATION")
class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        binding.rvNotification.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.rvNotification.layoutManager = layoutManager

        val listNotif = getListNotif()

        getData(listNotif)
    }

    private fun getData(listNotif: java.util.ArrayList<Notification>) {
        val adapter = NotificationAdapter(listNotif)
        binding.rvNotification.adapter = adapter
    }

    private fun getListNotif(): ArrayList<Notification> {
        val list = ArrayList<Notification>()
        list.add(
            Notification(
                R.drawable.img_news1,
                "Yeay! Study Tour",
                getString(R.string.desc_news1)
            )
        )
        list.add(
            Notification(
                R.drawable.img_news2,
                "Ujian Akhir Semester",
                getString(R.string.desc_news2)
            )
        )
        list.add(
            Notification(
                R.drawable.img_news3,
                "Pembagian Rapot",
                getString(R.string.desc_news3)
            )
        )
        return list
    }
}