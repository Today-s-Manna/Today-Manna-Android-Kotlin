package com.example.todaysmannanative

import android.content.Context
import android.widget.Toast

fun toast(message: String, duration: Int = 0){
    var durationTime = Toast.LENGTH_SHORT
    if(duration != 0) durationTime = Toast.LENGTH_LONG
    Toast.makeText(MainApplication.applicationContext(), message, durationTime).show()
}
