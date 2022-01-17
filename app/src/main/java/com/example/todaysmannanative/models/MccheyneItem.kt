package com.example.todaysmannanative.models
import kotlinx.serialization.*

@Serializable
data class MccheynePlan (
    val plan: Map<String, List<CheckItem>>
)

@Serializable
data class CheckItem (
    val isChecked: Boolean,
    val book: String,
    val chapter: Long,
    val verses: List<Verse>
)

@Serializable
data class Verse (
    val verse: Long,
    val content: String
)
