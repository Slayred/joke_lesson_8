package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.model.JokeUIModel

interface JokeCallback {

    fun provide(jokeUIModel: JokeUIModel)
    //fun fail(error: ErrorType)
}