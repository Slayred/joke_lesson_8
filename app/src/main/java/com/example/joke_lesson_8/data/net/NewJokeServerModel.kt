package com.example.joke_lesson_8.data.net

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.CommonDataModel
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

) : Mapper<CommonDataModel<Int>> {
    override fun to(): CommonDataModel<Int> {
        return CommonDataModel(id,setup, punchline)
    }
}