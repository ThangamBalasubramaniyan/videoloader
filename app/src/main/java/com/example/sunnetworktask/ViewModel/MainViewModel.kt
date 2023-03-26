package com.example.sunnetworktask.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunnetworktask.ApiService.Api
import com.example.sunnetworktask.ApiService.RetrofitService
import com.example.sunnetworktask.Data.ImageList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    var myResponseList: MutableLiveData<ImageList> = MutableLiveData()
    var apiInterface: Api = RetrofitService().getApiClient()!!.create(Api::class.java)

    fun getImages() {
        apiInterface.getImages().enqueue(object : Callback<ImageList> {

            override fun onResponse(call: Call<ImageList>, response: Response<ImageList>) {
                myResponseList.value = response?.body()!!
                println("Thangam ${myResponseList.value}")
            }
            override fun onFailure(call: Call<ImageList>, t: Throwable) {
                Log.e("Thangam",t.message.toString())
            }
        })
    }
}