package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.domain.Joke

interface JokeCachedCallback {

    fun provide(joke: Joke)

    fun fail()
}