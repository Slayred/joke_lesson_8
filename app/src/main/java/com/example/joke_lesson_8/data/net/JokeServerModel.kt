package com.example.joke_lesson_8.data.net

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.CommonDataModel
import com.google.gson.annotations.SerializedName

class JokeServerModel (
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val setup: String,
    @SerializedName("punchline")
    private val punchline: String

    ) : Mapper<CommonDataModel<Int>>
{
    override fun to() = CommonDataModel(id,setup,punchline)
}