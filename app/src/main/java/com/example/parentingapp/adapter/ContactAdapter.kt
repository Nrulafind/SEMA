package com.example.parentingapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.R.*
import com.example.parentingapp.data.Contact
import com.example.parentingapp.databinding.ItemChatBinding
import com.example.parentingapp.ui.ChatDetailActivity
import com.google.android.play.core.integrity.client.R
class ContactAdapter(private var contactList: ArrayList<Contact>, private var fromUser: Contact?) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.binding.tvItemName.text = contact.nama
//        holder.binding.email.text = contact
        holder.binding.imgItemPhoto.setImageResource(drawable.ic_baseline_account_circle_24)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ChatDetailActivity::class.java)
            intent.putExtra("fromUser", fromUser)
            intent.putExtra("toUser", contact)
            intent.putExtra("roomId", "noRoomId")
            holder.itemView.context.startActivity(intent)
        }
    }
}