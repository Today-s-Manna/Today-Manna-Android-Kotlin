package com.example.todaysmannanative.dataManagers

import android.util.Log
import android.widget.Toast
import com.example.todaysmannanative.models.MannaItem
import com.example.todaysmannanative.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MannaManager {
    companion object {

        var mannaItem: MannaItem = MannaItem()

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
                        val verse = it.verse
                        val contents = it.contents

                        mannaItem = MannaItem(verse, contents)

                        toast("verse: $verse\ncontents: $contents\n", Toast.LENGTH_LONG)
                    } ?: toast("api 구조 에러", Toast.LENGTH_SHORT)
                    try {
                        Log.d(
                            "Manna",
                            "verse: ${MannaManager.mannaItem.verse}\ncontents: ${MannaManager.mannaItem.contents}\n"
                        )
                    } catch (ex: Exception) {
                        Log.d("Exception occurred!", ex.message.toString())
                    }
                }
            })
        }
    }
}