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
        @ExperimentalSerializationApi
        fun parseMccheyneFromJson(): MccheynePlan {
            val assetManager = MainApplication.applicationContext().resources.assets
            val inputStream = assetManager.open("plan.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val mccheynePlan = Json.decodeFromString<MccheynePlan>(jsonString)

            val prettyJson = Json { prettyPrint = true }
            val deptJson = prettyJson.encodeToString(mccheynePlan)
            val deptFromJson = Json.decodeFromString<MccheynePlan>(deptJson)

            Log.d("TAG", deptFromJson.toString())

            return deptFromJson
        }
    }
}
