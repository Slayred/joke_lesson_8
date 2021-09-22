package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.BaseJoke
import com.example.joke_lesson_8.interfaces.CacheDataSource
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

    )
{
    fun toJoke() = BaseJoke(setup,punchline)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
}