package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.ErrorType
import com.example.joke_lesson_8.Joke
import com.example.joke_lesson_8.model.JokeServerModel

interface JokeCallback {

    fun provide(joke: Joke)
    fun fail(error: ErrorType)
}