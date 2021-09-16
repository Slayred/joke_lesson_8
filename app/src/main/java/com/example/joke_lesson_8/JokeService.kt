package com.example.joke_lesson_8

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("https://yesno.wtf/api")
    fun getJoke(): Call<JokeDTO>
}

interface ServiceCallback{

    fun returnSuccess(data: JokeDTO)

    fun returnError(type: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    OTHER,
    SLL_ERROR
}