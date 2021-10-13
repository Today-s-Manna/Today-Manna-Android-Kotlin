package com.example.todaysmannanative.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todaysmannanative.dataManagers.MannaApi
import com.example.todaysmannanative.dataManagers.MannaManager
import com.example.todaysmannanative.models.MannaItem
import com.example.todaysmannanative.toast
import kotlinx.coroutines.awaitAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        getData()
//        value = "${MannaManager.mannaItem.verse}\n"//${ mannaItem.contents}\n"
    }
    val text: LiveData<String> = _text

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

                    val mannaItem = MannaItem(verse, contents)

                    try {
                        Log.d(
                            "Manna",
                            "verse: ${mannaItem.verse}\ncontents: ${mannaItem.contents}\n"
                        )
                    } catch (ex: Exception) {
                        Log.d("Exception occurred!", ex.message.toString())
                    }

                    _text.postValue("verse: ${mannaItem.verse}\ncontents: ${mannaItem.contents}\n")

                } ?: toast("api 구조 에러", Toast.LENGTH_SHORT)
            }
        })
    }
}