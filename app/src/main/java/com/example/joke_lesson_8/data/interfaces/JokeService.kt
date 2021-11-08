package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.data.JokeServerModel
import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("/jokes/random")
    fun getJokeFromAPI(): Call<JokeServerModel>
}

