package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.JokeServerModel
import com.example.joke_lesson_8.data.ErrorType

interface ServiceCallback{

    fun returnSuccess(data: JokeServerModel)

    fun returnError(type: ErrorType)
}