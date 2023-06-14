package com.example.parentingapp.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.R
import com.example.parentingapp.data.News
import com.example.parentingapp.databinding.CourseItemBinding
import com.example.parentingapp.databinding.ItemNewsBinding
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(private var listNews: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val originalList = mutableListOf<News>()
    private val filteredList = mutableListOf<News>()

    fun filter(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(listNews)
        } else {
            val searchQuery = query.toLowerCase(Locale.getDefault())
            for (item in listNews) {
                if (item.title.toLowerCase(Locale.getDefault()).contains(searchQuery)) {
                    filteredList.add(item)
                }
            }
        }
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