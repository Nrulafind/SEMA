package com.example.parentingapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.data.Course
import com.example.parentingapp.data.Score
import com.example.parentingapp.databinding.CourseItemBinding
import com.example.parentingapp.databinding.ItemScoreBinding
import com.example.parentingapp.ui.ScoreDetailActivity

class ScoreAdapter (private var listScore: ArrayList<Score>) : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    class ScoreViewHolder(var binding: ItemScoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val binding = ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScoreViewHolder(binding)
    }

    override fun getItemCount(): Int = listScore.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val scoreData = listScore[position]
        holder.binding.namaUjian.text = scoreData.name
        holder.binding.score.text = scoreData.score
        holder.binding.kkm.text = scoreData.kkm
//        holder.binding.courseIcon.setImageResource(courseData.image)
//        holder.binding.courseName.text = courseData.title

//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context, ScoreDetailActivity::class.java).apply {
//                putExtra(EXTRA_COURSE, courseData.title)
//            }
//
//            holder.itemView.context.startActivity(intent)
//        }
    }

//    companion object{
//        const val EXTRA_COURSE = "extra_course"
//    }
}