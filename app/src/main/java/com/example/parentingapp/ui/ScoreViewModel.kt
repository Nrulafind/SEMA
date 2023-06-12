package com.example.parentingapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parentingapp.api.ApiConfig
import com.example.parentingapp.data.PredictResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScoreViewModel : ViewModel() {

    var predict = MutableLiveData(0.0)
    companion object {
        private const val TAG = "ScoreViewModel"
    }

    fun getPredict(input: ArrayList<Int>) {
        viewModelScope.launch {
            val client = ApiConfig.getApiService().getPredict(input)
            client.enqueue(object : Callback<PredictResponse>{
                override fun onResponse(
                    call: Call<PredictResponse>,
                    response: Response<PredictResponse>
                ) {
                    if (response.isSuccessful) {
                        predict.value = response.body()?.value
                    } else {
//                        Log.d(TAG, "$input")
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        }
    }
}