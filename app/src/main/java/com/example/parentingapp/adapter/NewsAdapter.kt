package com.example.parentingapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.News
import com.example.parentingapp.databinding.ItemNewsBinding
import com.example.parentingapp.ui.NewsDetailActivity
import com.example.parentingapp.ui.NotifDetailActivity
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

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, NewsDetailActivity::class.java).apply {
                putExtra(EXTRA_NEWS, newsData)
            }

            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }
}