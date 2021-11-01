package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.ErrorType
import com.example.joke_lesson_8.domain.Joke

interface JokeCloudCallback {
    fun provide(joke: Joke)
    fun fail(error: ErrorType)
}