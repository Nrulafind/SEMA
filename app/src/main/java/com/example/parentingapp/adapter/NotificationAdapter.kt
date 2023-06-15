package com.example.parentingapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.Notification
import com.example.parentingapp.databinding.ItemNotificationBinding
import com.example.parentingapp.ui.NotifDetailActivity

class NotificationAdapter (private var listNotif: ArrayList<Notification>): RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: ItemNotificationBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listNotif.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val newsData = listNotif[position]
        holder.binding.imgItemPhoto.setImageResource(newsData.image)
        holder.binding.tvItemName.text = newsData.title
        holder.binding.notificationDesc.text = newsData.description

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, NotifDetailActivity::class.java).apply {
                putExtra(EXTRA_NOTIF, newsData)
            }

            holder.itemView.context.startActivity(intent)
        }
    }

    companion object{
        const val EXTRA_NOTIF = "extra_notif"
    }
}