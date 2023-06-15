package com.example.parentingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parentingapp.R
import com.example.parentingapp.data.User
import com.example.parentingapp.databinding.ItemChatBinding

class ContactAdapter(var context: Context, var contactList: ArrayList<User>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val binding: ItemChatBinding = ItemChatBinding.bind(itemView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.binding.tvItemName.text = contact.name
        Glide.with(context).load(contact.photoUrl)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .into(holder.binding.imgItemPhoto)
    }
}