package com.example.parentingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.databinding.ItemAttendanceBinding

class AttendanceAdapter(): RecyclerView.Adapter<AttendanceAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: ItemAttendanceBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val newsData = listNews[position]
//        holder.binding.ivNews.setImageResource(newsData.image)
//        holder.binding.tvNewsTitle.text = newsData.title
//        holder.binding.tvNewsDescription.text = newsData.description
    }
}