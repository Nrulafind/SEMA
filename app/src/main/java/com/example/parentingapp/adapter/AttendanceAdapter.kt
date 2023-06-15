package com.example.parentingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.R
import com.example.parentingapp.data.Attendance
import com.example.parentingapp.databinding.ItemAttendanceBinding
import android.content.Context

class AttendanceAdapter(private var listAttendance: ArrayList<Attendance>): RecyclerView.Adapter<AttendanceAdapter.MyViewHolder>() {

    val orderList = listAttendance.sortedBy { it.tanggal.toInt() }
    class MyViewHolder(var binding: ItemAttendanceBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.itemView.context
        val attendanceData = orderList[position]
        holder.binding.tvDate.text = attendanceData.tanggal
        holder.binding.tvDay.text = attendanceData.nama
        holder.binding.tvAttendance.text = attendanceData.keterangan

        if (attendanceData.keterangan == "Libur") {
            holder.binding.tvDate.setBackgroundColor(getColor(context, R.color.light_gray))
        }
        if (attendanceData.keterangan == "Sakit") {
            holder.binding.tvDate.setBackgroundColor(getColor(context, R.color.dark_orange))
        }
        if (attendanceData.keterangan == "Alpha") {
            holder.binding.tvDate.setBackgroundColor(getColor(context, R.color.dark_red))
        }
        if (attendanceData.keterangan == "Izin") {
            holder.binding.tvDate.setBackgroundColor(getColor(context, R.color.light_green))
        }
    }
}