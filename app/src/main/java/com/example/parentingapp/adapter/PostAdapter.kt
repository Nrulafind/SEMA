package com.example.parentingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.R
import com.example.parentingapp.data.News
import com.example.parentingapp.data.Post
import com.example.parentingapp.databinding.ItemNewsBinding
import com.example.parentingapp.databinding.ItemPostBinding

class PostAdapter(private var listPost: ArrayList<Post>): RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(listPost: ArrayList<Post>){
        this.listPost = listPost
        notifyDataSetChanged()
    }
    class MyViewHolder(var binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listPost.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postData = listPost[position]
        holder.binding.ivPost.setImageResource(postData.image)
        holder.binding.ivTeacher.setImageResource(postData.teacherPhoto)
        holder.binding.tvTeacher.text = postData.teacherName
        holder.binding.tvTimeUpload.text = postData.time
    }
}