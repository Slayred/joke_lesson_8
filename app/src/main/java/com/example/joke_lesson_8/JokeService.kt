package com.example.joke_lesson_8

interface JokeService {
    fun getJoke(callback: ServiceCallback)
}

interface ServiceCallback{

    fun returnSuccess(data: String)

    fun returnError(type: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    OTHER,
    SLL_ERROR
}