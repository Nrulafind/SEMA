package com.example.parentingapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.Month
import com.example.parentingapp.databinding.ItemMonthBinding
import com.example.parentingapp.ui.DetailAttendance

class MonthAdapter(private var listMonth: ArrayList<Month>) : RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {

    class MonthViewHolder(var binding: ItemMonthBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val binding = ItemMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonthViewHolder(binding)
    }

    override fun getItemCount(): Int = listMonth.size

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        val monthData = listMonth[position]
        holder.binding.courseIcon.setImageResource(monthData.image)
        holder.binding.courseName.text = monthData.title

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailAttendance::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }


}