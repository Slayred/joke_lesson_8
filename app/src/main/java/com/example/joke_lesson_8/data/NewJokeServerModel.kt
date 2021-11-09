package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.core.Mapper
import com.google.gson.annotations.SerializedName

class NewJokeServerModel (
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val setup: String,
    @SerializedName("delivery")
    private val punchline: String

) : Mapper<JokeDataModel> {
    override fun to(): JokeDataModel {
        return JokeDataModel(id,setup, punchline)
    }
}