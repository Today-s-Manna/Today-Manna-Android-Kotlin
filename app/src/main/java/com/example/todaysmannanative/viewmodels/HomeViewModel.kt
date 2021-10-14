package com.example.todaysmannanative.viewmodels

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todaysmannanative.models.MannaItem

class HomeViewModel : ViewModel() {

    val contentsLiveData = MutableLiveData<ArrayList<String>>()
    private val _text = MutableLiveData<String>()

    val text: LiveData<String> = _text

    fun updateValue(item:MannaItem){
        _text.postValue(item.verse)
        contentsLiveData.value = item.contents
    }
}