package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.model.Joke
import com.example.joke_lesson_8.model.JokeServerModel

interface JokeCachedCallback {

    fun provide(joke: Joke)

    fun fail()
}