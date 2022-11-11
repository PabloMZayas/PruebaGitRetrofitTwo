package com.example.retrofittwo

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("comments")
    fun getData(): Call<List<MyData2Item>>
}