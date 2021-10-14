package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.ErrorType
import com.example.joke_lesson_8.model.Joke
import com.example.joke_lesson_8.model.JokeServerModel

interface JokeCloudCallback {
    fun provide(joke: Joke)
    fun fail(error: ErrorType)
}