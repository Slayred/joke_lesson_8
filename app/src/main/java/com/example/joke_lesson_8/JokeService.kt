package com.example.joke_lesson_8

import com.example.joke_lesson_8.model.JokeServerModel
import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("/jokes/random")
    fun getJoke(): Call<JokeServerModel>
}

interface ServiceCallback{

    fun returnSuccess(data: JokeServerModel)

    fun returnError(type: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    OTHER,
    SLL_ERROR
}