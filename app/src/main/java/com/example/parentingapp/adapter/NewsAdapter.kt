package com.example.parentingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.R
import com.example.parentingapp.data.News
import com.example.parentingapp.databinding.CourseItemBinding
import com.example.parentingapp.databinding.ItemNewsBinding

class NewsAdapter(private var listNews: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val newsData = listNews[position]
        holder.binding.ivNews.setImageResource(newsData.image)
        holder.binding.tvNewsTitle.text = newsData.title
        holder.binding.tvNewsDescription.text = newsData.description
    }
}