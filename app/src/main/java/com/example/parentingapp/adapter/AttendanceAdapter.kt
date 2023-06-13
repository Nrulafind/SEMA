package com.example.parentingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.Attendance
import com.example.parentingapp.databinding.ItemAttendanceBinding

class AttendanceAdapter(private var listAttendance: ArrayList<Attendance>): RecyclerView.Adapter<AttendanceAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: ItemAttendanceBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listAttendance.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val attendanceData = listAttendance[position]
        holder.binding.tvDate.text = attendanceData.tanggal
        holder.binding.tvDay.text = attendanceData.nama
        holder.binding.tvAttendance.text = attendanceData.keterangan
    }
}