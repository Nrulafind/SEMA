package com.example.parentingapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parentingapp.api.ApiConfig
import com.example.parentingapp.data.Student
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentViewModel : ViewModel() {

    companion object {
        private const val TAG = "StudentViewModel"
    }

//    fun getData(token: String): Student {
//        var story = Student()
//        viewModelScope.launch {
//            val client = ApiConfig.getApiService().getStudentData(token)
//            client.enqueue(object : Callback<Student> {
//                override fun onResponse(
//                    call: Call<Student>,
//                    response: Response<Student>,
//                ) {
//                    if (response.isSuccessful) {
//                        story = response.body()!!
//                    } else {
//                        Log.e(TAG, "onFailure: ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<Student>, t: Throwable) {
//                    Log.e(TAG, "onFailure: ${t.message.toString()}")
//                }
//            })
//        }
//        return story
//    }
}