package com.example.parentingapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.Course
import com.example.parentingapp.databinding.CourseItemBinding
import com.example.parentingapp.ui.ScoreDetailActivity

class CourseAdapter(private var listCourse: ArrayList<Course>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

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

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ScoreDetailActivity::class.java).apply {
                putExtra(EXTRA_COURSE, courseData.title)
            }

            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}