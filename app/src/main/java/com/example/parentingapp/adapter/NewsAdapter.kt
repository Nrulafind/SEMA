package com.example.parentingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.News
import com.example.parentingapp.databinding.ItemNewsBinding
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(private var listNews: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(listNews: ArrayList<News>){
        this.listNews = listNews
        notifyDataSetChanged()
    }

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