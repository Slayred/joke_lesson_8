package com.example.joke_lesson_8

import com.google.gson.annotations.SerializedName

class JokeDTO (
    @SerializedName("answer")
    private val answer: String,
    @SerializedName("forced")
    private val forced: String,
    @SerializedName("image")
    private val image: String
    )
{
    fun toJoke() = Joke(answer,image)
}