package com.example.parentingapp.api

import com.example.parentingapp.data.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
//    @GET("stories")
//    suspend fun getStories(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int,
//    ): StoriesResponse

    @GET("api/private/student")
    fun getStudentData(
        @Header("Authorization") token: String
    ): Call<StudentResponse>
}