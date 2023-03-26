package com.example.sunnetworktask.ApiService

import com.example.sunnetworktask.Data.ImageList
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("data.json")
    fun getImages() : Call<ImageList>
}