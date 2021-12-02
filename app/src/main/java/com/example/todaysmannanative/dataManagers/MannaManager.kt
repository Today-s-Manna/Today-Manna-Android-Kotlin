package com.example.todaysmannanative.dataManagers

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.todaysmannanative.MainApplication
import com.example.todaysmannanative.models.MannaItem
import com.example.todaysmannanative.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MannaManager {
    companion object {

        var mannaItem = MannaItem()

        fun getData() {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://3.138.184.130:9179/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(MannaApi::class.java)

            service.getMannaData().enqueue(object : Callback<MannaItem> {

                override fun onFailure(call: Call<MannaItem>, t: Throwable) {
                    t.message?.let {
                        toast(it, Toast.LENGTH_SHORT)
                    } ?: toast("요청 실패", Toast.LENGTH_SHORT)
                }

                override fun onResponse(call: Call<MannaItem>, response: Response<MannaItem>) {
                    response.body()?.let {
                        val intent = Intent("mannaData")

                        mannaItem = it

                        intent.putExtra("item", it)

                        LocalBroadcastManager.getInstance(MainApplication.applicationContext()).sendBroadcast(intent)

                        try {
                            Log.d(
                                "Manna",
                                "verse: ${it.verse}\ncontents: ${it.contents}\n"
                            )
                        } catch (ex: Exception) {
                            Log.d("Exception occurred!", ex.message.toString())
                        }
                    } ?: toast("api 구조 에러", Toast.LENGTH_SHORT)
                }
            })
        }
    }
}