package com.example.parentingapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.parentingapp.data.Course
import com.example.parentingapp.data.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScoreDetailViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    fun getCourseData(title: String): StateFlow<Course> = MutableStateFlow(
        courseRepository.getCourseByName(title)
    ).asStateFlow()
}