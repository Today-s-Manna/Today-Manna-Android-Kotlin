package com.example.todaysmannanative.dataManagers

import android.util.Log
import com.example.todaysmannanative.MainApplication
import com.example.todaysmannanative.models.MccheynePlan
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MccheyneManager {
    companion object {
        private lateinit var mccheynePlan: MccheynePlan

        @ExperimentalSerializationApi
        fun parseMccheyneFromJson() {
            val assetManager = MainApplication.applicationContext().resources.assets
            val inputStream = assetManager.open("plan.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            mccheynePlan = Json.decodeFromString(jsonString)
        }
    }
}
