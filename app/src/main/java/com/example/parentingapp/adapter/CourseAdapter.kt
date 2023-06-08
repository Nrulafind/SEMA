package com.example.parentingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.Course
import com.example.parentingapp.databinding.CourseItemBinding

class CourseAdapter (private var listCourse: ArrayList<Course>) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(var binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun getItemCount(): Int = listCourse.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseData = listCourse[position]
        holder.binding.courseIcon.setImageResource(courseData.image)
        holder.binding.courseName.text = courseData.title
    }


}