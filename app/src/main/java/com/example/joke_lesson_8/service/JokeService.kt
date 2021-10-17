package com.example.joke_lesson_8.service

import com.example.joke_lesson_8.model.JokeServerModel
import retrofit2.http.GET

interface JokeService {
    @GET("/jokes/random")
    suspend fun getJoke(): JokeServerModel
}

