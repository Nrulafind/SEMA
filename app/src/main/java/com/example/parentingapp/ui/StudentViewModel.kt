package com.example.parentingapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parentingapp.api.ApiConfig
import com.example.parentingapp.data.StudentResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentViewModel : ViewModel() {

    companion object {
        private const val TAG = "StudentViewModel"
    }

    fun getData(token: String): StudentResponse {
        var story = StudentResponse()
        viewModelScope.launch {
            val client = ApiConfig.getApiService().getStudentData(token)
            client.enqueue(object : Callback<StudentResponse> {
                override fun onResponse(
                    call: Call<StudentResponse>,
                    response: Response<StudentResponse>,
                ) {
                    if (response.isSuccessful) {
                        story = response.body()!!
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        return story
    }
}