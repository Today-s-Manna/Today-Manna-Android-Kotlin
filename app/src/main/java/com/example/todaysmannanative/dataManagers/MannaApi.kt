package com.example.todaysmannanative.dataManagers

import com.example.todaysmannanative.models.MannaItem
import retrofit2.Call
import retrofit2.http.GET

interface MannaApi {
    @GET("api/v1/today-manna")
    fun getMannaData(): Call<MannaItem>
}