package com.example.parentingapp.api

import com.example.parentingapp.data.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
//    @Multipart
//    @FormUrlEncoded
    @POST("api/predict")
    @FormUrlEncoded
//    @Multipart
    fun getPredict(
    @Header("Content-Type") tipe: String = "application/json",
    @Field("input_data") input_data : ArrayList<Int>,
//        @Body input_data : Input
//        @Part("input_data[]") input_data : List<Int>,
    ): Call<PredictResponse>
}