package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.model.JokeServerModel
import com.example.joke_lesson_8.data.ErrorType

interface CloudDataSource {

    suspend fun getJoke() : Result<JokeServerModel, ErrorType>
}