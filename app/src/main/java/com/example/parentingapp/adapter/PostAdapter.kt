package com.example.parentingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.R

class PostAdapter: RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 25
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }
}