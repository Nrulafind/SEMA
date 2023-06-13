package com.example.parentingapp.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parentingapp.api.ApiConfig
import com.example.parentingapp.data.PredictResponse
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header

class ScoreViewModel : ViewModel() {

    private val _predict = MutableLiveData<PredictResponse>()
    val predict: LiveData<PredictResponse> = _predict

    //    private var predict
    companion object {
        private const val TAG = "ScoreViewModel"
        private val tipe = "application/json"
    }

    fun getPredict(input: ArrayList<Int>) {
        val client = ApiConfig.getApiService().getPredict(tipe, input)
        client.enqueue(object : Callback<PredictResponse> {
            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.cause}")
            }

            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>,
            ) {
                if (response.isSuccessful) {
                    _predict.value = response.body()
                }
                else {
//                    Log.e(TAG, "onFailure: ${response.body()?.results}")
                    Log.d(TAG, "$input")
//                    _predict.value = response.body()?.results
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
        })
    }

//    fun getPredict(input: ArrayList<Double>) {
//        val client = AsyncHttpClient()
//        val url = "https://backendsema-x6blq7wjaa-et.a.run.app/api/predict"
//
//        val params = RequestParams()
//        params.put("input_data", input)
//
//        client.post(url,params, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
//                val result = String(responseBody)
//                Log.d(TAG, result)
//                try {
//                    val responseObject = JSONObject(result)
//                    val quote = responseObject.getDouble("result")
//                    _predict.value = quote
//                } catch (e: Exception) {
//                    Log.d(TAG, e.cause)
//                }
//            }
//            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
//                headers?.let {
//                    val errorMessage = when (statusCode) {
//                        401 -> "$statusCode : Bad Request"
//                        403 -> "$statusCode : Forbidden"
//                        404 -> "$statusCode : Not Found"
//                        else -> "$statusCode : ${error?.message}"
//                    }
//                    Toast.makeText(this@ScoreDetailActivity, errorMessage, Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }
}