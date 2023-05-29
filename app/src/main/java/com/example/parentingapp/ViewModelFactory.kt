package com.example.parentingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parentingapp.data.CourseRepository
import com.example.parentingapp.ui.detail.ScoreDetailViewModel

class ViewModelFactory(private val repository: CourseRepository) :
ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreDetailViewModel::class.java)) {
            return ScoreDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}