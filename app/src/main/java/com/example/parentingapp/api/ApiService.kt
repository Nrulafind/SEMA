package com.example.parentingapp.api

import com.example.parentingapp.data.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("predict")
    @FormUrlEncoded
    fun getPredict(
        @Field("input_data") input_data: ArrayList<Int>,
    ): Call<PredictResponse>
}