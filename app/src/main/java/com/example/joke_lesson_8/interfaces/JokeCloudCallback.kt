package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.service.ErrorType
import com.example.joke_lesson_8.model.Joke

interface JokeCloudCallback {
    fun provide(joke: Joke)
    fun fail(error: ErrorType)
}